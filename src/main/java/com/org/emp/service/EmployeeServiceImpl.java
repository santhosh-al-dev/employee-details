package com.org.emp.service;

import com.org.emp.data.BaseResponse;
import com.org.emp.data.apiresponse.EmployeeBaseResponse;
import com.org.emp.entity.EmployeeEntity;
import com.org.emp.exceptionhandling.ResourceNotFoundException;
import com.org.emp.processor.EmployeeRequestProcessor;
import com.org.emp.repository.EmployeeRepository;
import com.org.emp.utility.EmployeeUtils;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRequestProcessor employeeRequestProcessor;

    private final EmployeeUtils employeeUtils;

    private final  EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRequestProcessor employeeRequestProcessor, EmployeeUtils employeeUtils,
                               EmployeeRepository employeeRepository){
        this.employeeRequestProcessor=employeeRequestProcessor;
        this.employeeUtils=employeeUtils;
        this.employeeRepository=employeeRepository;

    }


    @Override
    public BaseResponse saveEmployee() {
        EmployeeBaseResponse employeeBaseResponse = employeeRequestProcessor.createEmployee();

        //Processor to save records in db
        // Extract the list of EmployeeDataResponse objects
        List<EmployeeBaseResponse.EmployeeDataApiResponse> employeeDataApiRespons = employeeBaseResponse.getData();
        // Save all employees and get the saved entities
        List<EmployeeEntity> savedEntities = saveAllEmployees(employeeDataApiRespons);
        logger.debug(" save: {}", savedEntities);


        // Prepare the success flag and message based on the save operation result
        boolean isSuccess = "success".equalsIgnoreCase(employeeBaseResponse.getSuccess());
        String message = isSuccess ? "Employees fetched successfully" : "Failed to get employees";
        return BaseResponse.builder()
                .success(isSuccess)
                .message(message)
                .data(employeeUtils.getListData(employeeBaseResponse)) // Assuming this is the desired data format
                .build();
    }

    @Override
    public List<EmployeeEntity> getAllEmployees(){
        return new ArrayList<EmployeeEntity>(employeeRepository.findAll());
    }


    @Transactional
    public List<EmployeeEntity> saveAllEmployees(List<EmployeeBaseResponse.EmployeeDataApiResponse> employeeDataApiRespons) {
        try {
        List<EmployeeEntity> employeeEntities = employeeUtils.convertToEntityList(employeeDataApiRespons);
        logger.debug("Entity before save: {}", employeeEntities);
        return employeeRepository.saveAll(employeeEntities);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Unable to store data in database");
        }
    }



//
//
//
//    @Override
//    public EmployeeResponse getEmployeeById(@PathVariable Long id) {
//        Optional<EmployeeResponse> employee = employeeRepository.findById(id);
//        return employee.orElseThrow(() ->
//                new ResourceNotFoundException("Employee not found for this ID:" + id));
//    }
//
//    @Override
//    public ResponseEntity<EmployeeResponse> updateEmployeeById(@PathVariable Long id, @RequestBody EmployeeResponse.EmployeeDataResponse getEmployeeDetails) throws ResourceNotFoundException {
//        try {
//            EmployeeResponse employee = employeeRepository.findById(id)
//                    .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
//
//            employee.getData()
//        (getEmployeeDetails.getEmployeeAge());
//            employee.getEmployeeName(getEmployeeDetails.getEmployeeName());
//            employee.getEmployeeSalary(getEmployeeDetails.getEmployeeSalary());
//
//            Employee updatedEmployee = employeeRepository.save(employee);
//            return ResponseEntity.ok(updatedEmployee);
//
//        } catch (ResourceNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
//
//            @Override
//            public ResponseEntity<String> deleteEmployeeById(@PathVariable Long id) {
//                if (employeeRepository.existsById(id)) {
//                    employeeRepository.deleteById(id);
//                    return new ResponseEntity<>(id + " Record Deleted Successfully", HttpStatus.OK);
//                } else {
//                    return new ResponseEntity<>("Employee not found with ID: " + id
//                            ,HttpStatus.BAD_REQUEST);
//                }
//            }
        }

