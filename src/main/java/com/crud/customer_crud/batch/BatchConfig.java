package com.crud.customer_crud.batch;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.crud.customer_crud.entity.Customer;

public class BatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;
	
	@Bean
	public Job firstJob() {
		return jobBuilderFactory.get("First Job")
				.incrementer(new RunIdIncrementer())
				.start(firstChunkStep())
				.next(secondStep())
				.build();
	}
	public Step firstChunkStep() {
		return stepBuilderFactory.get("First Chunk Step")
				.<Customer, Customer>chunk(3)
				.reader(flatFileItemReader())
				//.processor(firstItemProcessor)
				.writer(jsonFileItemWriter())
				.faultTolerant()
				.skip(FlatFileParseException.class)
				.skipLimit(Integer.MAX_VALUE)
				.build();
	}
	
	public Step secondStep() {
		return stepBuilderFactory.get("Second Step")
				.tasklet(secondTasklet())
				.build();
	}
	/*
	 * file: where out csv file is located
	 *
	 * FlatFileItemReader:
	 * 		- Responsible for reading the csv file.
	 * 		- Needs a LineMapper
	 *
	 *	LineMapper:
	 *		- Needs a LineTokenizer & FieldSetMapper 							
	 */
	public FlatFileItemReader<Customer> flatFileItemReader() {
		File file = new File("/home/Desktop/code/crud-application-vishesh/file.csv");
		
		FlatFileItemReader<Customer> flatFileItemReader = new FlatFileItemReader<>();
		flatFileItemReader.setResource(new FileSystemResource(file));
		
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer("|");
		lineTokenizer.setNames("ID", "First Name", "Last Name", "Email");
		
		BeanWrapperFieldSetMapper<Customer> mapper = new BeanWrapperFieldSetMapper<>();
		mapper.setTargetType(Customer.class);
		
		DefaultLineMapper<Customer> lineMapper = new DefaultLineMapper<>();
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(mapper);
		
		flatFileItemReader.setLinesToSkip(1);
		
		flatFileItemReader.setLineMapper(lineMapper);
		
		return flatFileItemReader;
	}
	
	public JsonItemReader<Customer> jsonItemReader() {
		File file = new File("/home/vishesh/Desktop/code/crud-application-vishesh/file.json");
		
		JsonItemReader<Customer> itemReader = new JsonItemReader<>();
		itemReader.setResource(new FileSystemResource(file));
		
		JacksonJsonObjectReader<Customer> objectReader = new JacksonJsonObjectReader<>(Customer.class);
		
		itemReader.setJsonObjectReader(objectReader);
		
		return itemReader;
	}
	
	public JdbcCursorItemReader<Customer> jdbcCursorItemReader() {
		JdbcCursorItemReader<Customer> jdbcCursorItemReader = new JdbcCursorItemReader<>();
		
		jdbcCursorItemReader.setDataSource(dataSource);
		jdbcCursorItemReader.setSql("select id, first_name as firstName, last_name as lastName, email from student");
		
		BeanPropertyRowMapper<Customer> beanPropertyRowMapper = new BeanPropertyRowMapper<>();
		beanPropertyRowMapper.setMappedClass(Customer.class);
		
		jdbcCursorItemReader.setRowMapper(beanPropertyRowMapper);
		
		jdbcCursorItemReader.setCurrentItemCount(3);
		jdbcCursorItemReader.setMaxItemCount(8);
		
		return jdbcCursorItemReader;
	}
	public FlatFileItemWriter<Customer> fileItemWriter() {
		FlatFileItemWriter<Customer> fileItemWriter = new FlatFileItemWriter<>();
	
		File file = new File("/home/Documents/student.csv");
		FileSystemResource fileSystemResource = new FileSystemResource(file);
		
		fileItemWriter.setResource(fileSystemResource);
		
		FlatFileHeaderCallback headerCallback = new FlatFileHeaderCallback() {
			
			@Override
			public void writeHeader(Writer writer) throws IOException {
				writer.write("Id|First Name|Last Name|Email");
			}
		};
		
		fileItemWriter.setHeaderCallback(headerCallback);
		
		DelimitedLineAggregator<Customer> aggregator = new DelimitedLineAggregator<>();
		BeanWrapperFieldExtractor<Customer> extractor = new BeanWrapperFieldExtractor<>();
		extractor.setNames(new String[] {"id", "firstName", "lastName", "email"});
		aggregator.setFieldExtractor(extractor);
		aggregator.setDelimiter("|");
		
		fileItemWriter.setLineAggregator(aggregator);
		
		FlatFileFooterCallback footerCallback = new FlatFileFooterCallback() {
			
			@Override
			public void writeFooter(Writer writer) throws IOException {
				writer.write("Creaetd @ " + new Date());
			}

			
		};
		
		fileItemWriter.setFooterCallback(footerCallback);
		
		return fileItemWriter;
	}
	
	public JsonFileItemWriter<Customer> jsonFileItemWriter() {
		File file = new File("/home/vishesh/Documents/student.json");
		FileSystemResource fileSystemResource = new FileSystemResource(file);
		
		JacksonJsonObjectMarshaller<Customer> marshaller = new JacksonJsonObjectMarshaller<>();
		
		JsonFileItemWriter<Customer> fileItemWriter = new JsonFileItemWriter<>(fileSystemResource, marshaller);
		
		return fileItemWriter;
	}
	public JdbcBatchItemWriter<Customer> jdbcBatchItemWriter() {
		JdbcBatchItemWriter<Customer> jdbcBatchItemWriter = new JdbcBatchItemWriter<>();
		
		jdbcBatchItemWriter.setDataSource(dataSource);
		jdbcBatchItemWriter.setSql("insert into student(id, first_name, last_name, email) values(:id, :firstName, :lastName, :email)");
	
		BeanPropertyItemSqlParameterSourceProvider<Customer> sourceProvider = new BeanPropertyItemSqlParameterSourceProvider<>();
		
		jdbcBatchItemWriter.setItemSqlParameterSourceProvider(sourceProvider);
		
		jdbcBatchItemWriter.afterPropertiesSet();
		return jdbcBatchItemWriter;
	}
	
	public Tasklet secondTasklet() {
		return new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("This is second tasklet step");
				return RepeatStatus.FINISHED;
			}

		};
	}
}
