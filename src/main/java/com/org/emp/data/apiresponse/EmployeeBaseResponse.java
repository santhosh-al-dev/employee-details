package com.org.emp.data.apiresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeBaseResponse {

        @JsonProperty("status")
        private String success;

        @JsonProperty("message")
        private String message;

        @JsonProperty("data")
        private List<EmployeeDataApiResponse> data;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class EmployeeDataApiResponse {

                @JsonProperty("employee_name")
                private String employeeName;

                @JsonProperty("employee_age")
                private int employeeAge;

                @JsonProperty("employee_salary")
                private Double employeeSalary;

                @JsonProperty("email")
                private String emailId;
        }
}

