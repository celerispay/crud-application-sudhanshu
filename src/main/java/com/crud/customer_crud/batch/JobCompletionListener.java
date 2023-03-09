package com.crud.customer_crud.batch;

import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class JobCompletionListener extends JobExecutionListenerSupport {
	private JdbcTemplate jdbcTemplate;
	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("BATCH JOB COMPLETED SUCCESSFULLY");
		}
	}

}