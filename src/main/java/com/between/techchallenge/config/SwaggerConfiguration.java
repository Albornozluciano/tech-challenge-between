package com.between.techchallenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo()
                );
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Prices Service",
                "Brand and products prices service.",
                "1.0.0",
                "",
                new Contact("Luciano Julián Albornoz", "https://github.com/Albornozluciano/tech-challenge-between", "albornozlucianojulian@gmail.com"),
                "",
                "",
                new ArrayList<>()
        );

    }
}
