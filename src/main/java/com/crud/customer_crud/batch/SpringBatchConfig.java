package com.crud.customer_crud.batch;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import javax.sql.DataSource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DeadlockLoserDataAccessException;
import com.crud.customer_crud.entity.Customer;
import com.crud.customer_crud.entity.DemoCustomer;
import com.crud.customer_crud.repository.CustomerRepository;
import com.crud.customer_crud.repository.DemoCustomerRepository;

import lombok.extern.log4j.Log4j2;

@Configuration
@EnableBatchProcessing
@Log4j2
public class SpringBatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private DemoCustomerRepository demoCustomerRepository;

	@Autowired
	private DataSource dataSource;

	@Value("classPath:customers.csv")
	private Resource inputResource;
	
	private Resource outputResource = new FileSystemResource("output/outputData.csv");
	
	@Autowired
	CustomerProcessor customerProcessor;

	private static final String QUERY_FIND_CUSTOMERS = "SELECT " + "customer_name, " + "address, " + "contact_no "
			+ "FROM CUSTOMER " + "ORDER BY customer_name";

	@Bean
	public FlatFileItemReader<Customer> flatFileItemReader() {
		FlatFileItemReader<Customer> itemReader = new FlatFileItemReader<>();
		itemReader.setResource(inputResource);
		itemReader.setName("csvReader");
		itemReader.setLinesToSkip(1);
		itemReader.setLineMapper(lineMapper());
		return itemReader;
	}

	private LineMapper<Customer> lineMapper() {
		DefaultLineMapper<Customer> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames(new String[] { "customername", "address", "contactNo" });

		BeanWrapperFieldSetMapper<Customer> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Customer.class);

		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);

		return lineMapper;
	}

	@Bean
	public RepositoryItemWriter<Customer> repositoryItemWriter() {
		RepositoryItemWriter<Customer> writer = new RepositoryItemWriter<>();
		writer.setRepository(customerRepository);
		writer.setMethodName("save");
		return writer;
	}

	@Bean
	public RepositoryItemWriter<DemoCustomer> demoCustomerRepositoryItemWriter() {
		RepositoryItemWriter<DemoCustomer> writer = new RepositoryItemWriter<>();
		writer.setRepository(demoCustomerRepository);
		writer.setMethodName("save");
		return writer;
	}

	@Bean
	public JdbcCursorItemReader<Customer> jdbcCursorItemReader() {
		JdbcCursorItemReader<Customer> jdbcCursorItemReader = new JdbcCursorItemReader<>();
		jdbcCursorItemReader.setDataSource(dataSource);
		jdbcCursorItemReader.setSql(QUERY_FIND_CUSTOMERS);
		jdbcCursorItemReader.setRowMapper(new CustomerRowMapper());
		return jdbcCursorItemReader;
	}

	@Bean
	public FlatFileItemWriter<Customer> flatFileItemWriter() {
		FlatFileItemWriter<Customer> flatFileItemWriter = new FlatFileItemWriter<>();
		flatFileItemWriter.setResource(outputResource);
		flatFileItemWriter.setLineAggregator(new DelimitedLineAggregator<Customer>() {
			{
				setDelimiter(",");
				setFieldExtractor(new BeanWrapperFieldExtractor<Customer>() {
					{
						setNames(new String[] { "customerName", "address", "contactNo" });
					}
				});
			}
		});
		flatFileItemWriter.setHeaderCallback(writer -> writer.write("customername,address,contactNo"));
		return flatFileItemWriter;
	}

	@Bean
	public Step importCustomersFromCSV() {
		System.out.println("importCustomersFromCSV step is running");
		
		return stepBuilderFactory.get("csv-step")
				.allowStartIfComplete(true)
				.<Customer, Customer>chunk(10).reader(flatFileItemReader())
				.writer(repositoryItemWriter())
				.faultTolerant()
				.retryLimit(3)
			    .retry(DeadlockLoserDataAccessException.class)
				.skipLimit(10)
				.skip(Exception.class)
				.noSkip(FileNotFoundException.class)
				.build();
	}

	@Bean
	public Step importCustomersFromDbToDb() {
		System.out.println("importCustomersFromDbToDb step is running");
		return stepBuilderFactory.get("db-step")
				.<Customer, DemoCustomer>chunk(10)
				.reader(jdbcCursorItemReader())
				.processor(customerProcessor)
				.writer(demoCustomerRepositoryItemWriter())
				.faultTolerant()
				.skipLimit(10)
				.skip(Exception.class)
				.noSkip(SQLException.class)
				.build();
	}

	@Bean
	public Step importCustomersFromDbToCsv() {
		System.out.println("importCustomersFromDbToCsv step is running");
		return stepBuilderFactory.get("to-csv-step")
				.<Customer, Customer>chunk(10).reader(jdbcCursorItemReader())
				.writer(flatFileItemWriter())
				.faultTolerant()
				.skipLimit(10)
				.skip(Exception.class)
				.noSkip(FileNotFoundException.class)
				.startLimit(1)
				.build();
	}

	@Qualifier("myJob")
	@Bean
	Job myJob() {
		return jobBuilderFactory.get("importcustomersfromcsv")
				.listener(listener())
				.start(importCustomersFromCSV())
				.on("*").to(importCustomersFromDbToDb())
				.on("*").to(importCustomersFromDbToCsv())
				.from(importCustomersFromCSV()).on("FAILED").to(importCustomersFromDbToCsv())
				.end()		
				.build();
	}
	
	@Bean
	JobExecutionListener listener() {
		return new JobCompletionListener();
	}

}
