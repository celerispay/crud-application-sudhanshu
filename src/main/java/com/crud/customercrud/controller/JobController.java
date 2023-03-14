package com.crud.customercrud.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class JobController {

	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	private Job job;

	@Operation(summary = "Perform the Spring batch Task",description = "This API will import the customer data from csv file to the data base using Spring batch",parameters = {})
	@GetMapping("/importCustomers")
	public String importCsvToDb() throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		JobParameters jobParameters = new JobParametersBuilder().addLong("startAt", System.currentTimeMillis())
				.toJobParameters();

		jobLauncher.run(job, jobParameters);
		log.debug("This api will be performing the batch processing task");
		return "Batch job has been invoked";

	}
}
