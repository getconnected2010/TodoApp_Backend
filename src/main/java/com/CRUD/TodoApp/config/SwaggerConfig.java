package com.CRUD.TodoApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //it configures which endpoints are available in swagger UI.
    @Bean
    public Docket swaggerBean(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)) //this only exposes those API's with rest controllers. None of error api's show up.
                .paths(PathSelectors.any())
                .build();
    }

    //displays this info on the Swagger UI.
    private ApiInfo apiDetails(){
        return new ApiInfo(
                "Todo List API",
                "A todo app with list of users and tasks",
                "free",
                "getconnected2010",
                null,
                "none",
                "none",
                Collections.emptyList()
        );
    }
}
