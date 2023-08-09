package com.example.demo.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@Configuration
@EnableSwagger2
public class SwaggerConfig{

    private static final springfox.documentation.service.Contact DEFALUT_CONTACT
            = new Contact("Jang Heesu",
            "http://www.joneconsulting.co.kr",
            "huisujang80@gamil.com"); //Contect 정보

    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Awesome API Title",
            "My User management REST API service", "1.0", "urn:toes",
            DEFALUT_CONTACT, "Apache 2.0",
            "http://www.apache.org/licences/LICENSE-2.0",
            new ArrayList<>()); //API 정보

    private static final Set<String> DEFALUT_PRODUCES_AND_CONSUMES = new HashSet<>(
            Arrays.asList("application/json","application.xml"));
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFALUT_PRODUCES_AND_CONSUMES)
                .consumes(DEFALUT_PRODUCES_AND_CONSUMES);
    }
}