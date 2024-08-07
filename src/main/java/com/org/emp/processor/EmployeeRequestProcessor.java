package com.org.emp.processor;

import com.org.emp.data.apiresponse.EmployeeBaseResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownContentTypeException;

@Component //allows Spring to detect our custom beans automatically
public class EmployeeRequestProcessor {

    RestTemplate getEmployeeDetails;

    private final String getEmployeeDetailsUrl;

    private EmployeeRequestProcessor(@Qualifier("org-employee-details") RestTemplate getEmployeeDetails,
                                     @Value("${org.employees.api}") String getEmployeeDetailsUrl){ //Qualifier annotation in Spring is used to differentiate a bean among the same type of bean objects
        this.getEmployeeDetails= getEmployeeDetails;
        this.getEmployeeDetailsUrl= getEmployeeDetailsUrl;
    }

 public EmployeeBaseResponse createEmployee(){
     HttpHeaders httpHeaders = new HttpHeaders();
     return employeeEntity(getEmployeeDetailsUrl, httpHeaders, EmployeeBaseResponse.class);
    }

    public <T> T employeeEntity(String url, HttpHeaders httpHeaders, Class<T> tClass) {
        ResponseEntity<T> responseEntity;
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        try {
            responseEntity = getEmployeeDetails.getForEntity(url, tClass);
        } catch (UnknownContentTypeException ex) {
            throw ex; // or handle the exception as needed
        }
        return responseEntity.getBody();
    }

}
