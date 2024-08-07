package com.org.emp.service;

import com.org.emp.data.BaseResponse;
import com.org.emp.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeService {

        BaseResponse saveEmployee();

        List<EmployeeEntity> getAllEmployees();

//        EmployeeResponse getEmployeeById(Long id);
//
////        ResponseEntity<EmployeeResponse> updateEmployeeById(Long id, Employee employee);
//
//        ResponseEntity<String> deleteEmployeeById(Long id);
}
