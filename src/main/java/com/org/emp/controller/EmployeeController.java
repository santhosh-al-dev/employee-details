package com.org.emp.controller;

import com.org.emp.data.BaseResponse;
import com.org.emp.entity.EmployeeEntity;
import com.org.emp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/emp/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @PostMapping("/createEmployee")
    public BaseResponse registerEmployee(){
        return employeeService.saveEmployee();
    }

    @GetMapping("/getAllEmployee")
    public List<EmployeeEntity> getAllEmployee(){
        return employeeService.getAllEmployees();
    }

//    @GetMapping("/getEmployee/{id}")
//    public EmployeeResponse getEmployeeById(@PathVariable("id") Long Id){
//        return employeeService.getEmployeeById(Id);
//    }
//
//    @PutMapping("/updateEmployee/{id}")
//    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee empDetails){
//        return employeeService.updateEmployeeById(id, empDetails);
//    }
//
//    @DeleteMapping("/deleteEmployee/{id}")
//    public ResponseEntity<String> updateEmployee(@PathVariable("id") Long id){
//        return employeeService.deleteEmployeeById(id);
//    }


}
