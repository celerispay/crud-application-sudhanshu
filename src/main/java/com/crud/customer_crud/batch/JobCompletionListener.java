package com.crud.customer_crud.batch;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class JobCompletionListener extends JobExecutionListenerSupport {

	@Override
	public void afterJob(JobExecution jobExecution){
		log.debug("This is the message from job listener");
	    if (jobExecution.getStatus() == BatchStatus.COMPLETED ) {
	    	System.out.println("BATCH JOB COMPLETED SUCCESSFULLY");
	    }
	    else if (jobExecution.getStatus() == BatchStatus.FAILED) {
	        System.out.println("Batch Job has been failed to complete");
	    }
	}
}