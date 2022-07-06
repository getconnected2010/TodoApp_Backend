package com.CRUD.TodoApp.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
//@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig implements WebMvcConfigurer {

    //it configures which endpoints are available in swagger UI.
    @Bean
    public Docket swaggerConfigBean(){
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
