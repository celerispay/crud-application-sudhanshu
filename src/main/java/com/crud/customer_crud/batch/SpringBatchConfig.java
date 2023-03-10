package com.crud.customer_crud.batch;

import javax.sql.DataSource;

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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import com.crud.customer_crud.entity.Customer;
import com.crud.customer_crud.entity.DemoCustomer;
import com.crud.customer_crud.repository.CustomerRepository;
import com.crud.customer_crud.repository.DemoCustomerRepository;

import lombok.AllArgsConstructor;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
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

	@Autowired
	CustomerProcessor customerProcessor;
	
	private static final String QUERY_FIND_CUSTOMERS = "SELECT " + "customer_name, " + "address, " + "contact_no "
			+ "FROM CUSTOMER " + "ORDER BY customer_name";


	public FlatFileItemReader<Customer> flatFileItemReader() {
		FlatFileItemReader<Customer> itemReader = new FlatFileItemReader<>();
		itemReader.setResource(new FileSystemResource("src/main/resources/customers.csv"));
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

	public RepositoryItemWriter<Customer> repositoryItemWriter() {
		RepositoryItemWriter<Customer> writer = new RepositoryItemWriter<>();
		writer.setRepository(customerRepository);
		writer.setMethodName("save");
		return writer;
	}

	public RepositoryItemWriter<DemoCustomer> demoCustomerRepositoryItemWriter() {
		RepositoryItemWriter<DemoCustomer> writer = new RepositoryItemWriter<>();
		writer.setRepository(demoCustomerRepository);
		writer.setMethodName("save");
		return writer;
	}
	
	 @Bean
	    public JdbcCursorItemReader<Customer> jdbcCursorItemReader() {
	        JdbcCursorItemReader<Customer> jdbcCursorItemReader
	                = new JdbcCursorItemReader<>();
	        jdbcCursorItemReader.setDataSource(dataSource);
	        jdbcCursorItemReader.setSql(QUERY_FIND_CUSTOMERS);
	        jdbcCursorItemReader.setRowMapper(new CustomerRowMapper());
	        return jdbcCursorItemReader;
	    }
	
	@Bean
    public FlatFileItemWriter<Customer> flatFileItemWriter(){
        FlatFileItemWriter<Customer> flatFileItemWriter
                = new FlatFileItemWriter<>();
        flatFileItemWriter.setResource
          (new FileSystemResource
           ("C:\\Users\\Admin\\Desktop\\crud-application-sudhanshu/customersdbtocsv.csv"));
        flatFileItemWriter.setLineAggregator
           (new DelimitedLineAggregator<Customer>() 
           {{
             setDelimiter(",");
             setFieldExtractor(
             	new BeanWrapperFieldExtractor<Customer>() 
             {{
             	setNames(new String[]
             			{ "customerName", "address", "contactNo" });
             }});
            }});
        flatFileItemWriter.setHeaderCallback
                (writer -> writer.write("customername,address,contactNo"));
        return flatFileItemWriter;
    }

	public Step step1() {
		return stepBuilderFactory.get("csv-step")
				.<Customer, Customer>chunk(10)
				.reader(flatFileItemReader())
				.writer(repositoryItemWriter())
				.build();
	}

	public Step step2() {
		return stepBuilderFactory.get("db-step")
				.<Customer, DemoCustomer>chunk(10)
				.reader(jdbcCursorItemReader())
				.processor(customerProcessor)
				.writer(demoCustomerRepositoryItemWriter())
				.build();
	}
	
	public Step step3() {
		return stepBuilderFactory.get("to-csv-step")
				.<Customer, Customer>chunk(10)
				.reader(jdbcCursorItemReader())
				.writer(flatFileItemWriter())
				.build();
	}

//	@Qualifier("importcustomersfromcsv")
//	@Bean
//	Job importcustomersfromcsv() {
//		return jobBuilderFactory.get("importcustomersfromcsv")
//				.listener(listener())
//				.flow(step1())
//				.end()
//				.build();
//	}

//	@Bean
//	@Qualifier("importcustomersfromdbtodb")
//	Job importcustomersfromdbtodb() throws UnexpectedInputException, ParseException, Exception {
//		return jobBuilderFactory.get("importcustomersfromdbtodb")
//				.listener(listener())
//				.flow(step2())
//				.end()
//				.build();
//	}
	
	@Qualifier("generateCSVReportCard")
	@Bean
	Job generateCSVReportCard() {
		return jobBuilderFactory.get("generateCSVReportCard")
				.listener(listener())
				.flow(step3())
				.end()
				.build();
	}
	
	@Bean
	JobExecutionListener listener() {
		return new JobCompletionListener();
	}

}
