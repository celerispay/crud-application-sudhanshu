package com.crud.customercrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.log4j.Log4j2;



@SpringBootApplication
@Log4j2
@OpenAPIDefinition(info = @Info(title = "Customer CRUD API", version = "1.0", description = "API Documentation for Customer CRUD"))
public class CustomerCrudApplication {
	
  public static void main(String[] args) {
    SpringApplication.run(CustomerCrudApplication.class, args);
    log.info("Customer CRUD Application is running fine");
    log.info("Swagger UI url - "+" http://localhost:8080/swagger-ui/index.html");
  }
 

}