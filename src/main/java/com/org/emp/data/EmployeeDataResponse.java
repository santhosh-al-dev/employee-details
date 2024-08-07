package com.org.emp.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeDataResponse {

    @JsonProperty("employeeName")
    private String employeeName;

    @JsonProperty("employeeAge")
    private int employeeAge;

    @JsonProperty("employeeSalary")
    private Double employeeSalary;

    @JsonProperty("emailId")
    private String emailId;
}
