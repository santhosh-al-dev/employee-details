package com.org.emp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableTransactionManagement
public class EmployeeConfiguration {

    @Bean("org-employee-details")
    @Lazy //bean will be created and initialized only when it is first requested. Reduce the development time
    public RestTemplate employeeRestTemplate(){
        return new RestTemplate();
    }
}