package com.crud.customerCRUD;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.crud.customerCRUD.Controller.BankDetailsController;

import lombok.extern.log4j.Log4j2;



@SpringBootApplication
@EnableBatchProcessing
@Log4j2
public class CustomerCrudApplication {

	static Logger logger = LogManager.getLogger(BankDetailsController.class);
	
  public static void main(String[] args) {
    SpringApplication.run(CustomerCrudApplication.class, args);
    logger.info("I am running fine");
  }
 

}