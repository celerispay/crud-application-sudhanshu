//package com.crud.customerCRUD.config;
//
//import java.util.function.Predicate;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//@Configuration
//public class SwaggerConfig{
//
//	@Bean
//	Docket postsApi() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.groupName("customerCRUD-api")
//				.apiInfo(apiInfo()).select()
//				.apis(RequestHandlerSelectors.basePackage("com.crud.customerCRUD.Controller"))
//				.paths(PathSelectors.any())
//				.build();
//	}
//
//	private ApiInfo apiInfo() {
//		return new ApiInfoBuilder().title("CustomerCRUD API")
//				.description("Spring Rest API reference for developers")
//				.licenseUrl("sudhanshu@celerispay.com")
//				.version("1.0")
//				.build();
//	}
//
//}
