package com.dasun.stockservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

/**
 * The SwaggerConfig class for define swagger configurations
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * This method use to config swagger document
     *
     * @return :{Docket}
     */
    @Bean
    public Docket configuratorApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dasun.stockservice.stock"))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .apiInfo(metaData());
    }

    /**
     * This method use to create api information for the swagger document
     *
     * @return : {APIInfo}
     */
    private ApiInfo metaData() {
        return  new ApiInfo(
                "Stock Service",
                "Stock Service APIs",
                "",
                "",
                new Contact("Dasun","","dasunkanchana2@gmail.com"),
                "",
                "",
                Arrays.asList());
    }

}

