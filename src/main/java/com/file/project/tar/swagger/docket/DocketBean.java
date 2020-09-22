package com.file.project.tar.swagger.docket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.file.project.tar.file.utility.ServiceConstants;

import io.swagger.annotations.SwaggerDefinition;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@SwaggerDefinition
@EnableSwagger2
public class DocketBean {
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage(ServiceConstants.SWAGGER_BASE_PACKAGE)).build();
	}

}
