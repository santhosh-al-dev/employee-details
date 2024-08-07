package com.org.emp.utility;

import com.org.emp.data.EmployeeDataResponse;
import com.org.emp.data.apiresponse.EmployeeBaseResponse;
import com.org.emp.entity.EmployeeEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeUtils {

    public List<EmployeeDataResponse> getListData(EmployeeBaseResponse employeeBaseResponse) {
        List<EmployeeDataResponse> data = mapApiResponseToEmployeeDataResponseList(employeeBaseResponse);
        if (data == null) {
            data = new ArrayList<>();
        }
        return data;
    }

    public ArrayList<EmployeeBaseResponse.EmployeeDataApiResponse> updateBaseResponse(EmployeeBaseResponse employeeBaseResponse) {
        if (employeeBaseResponse != null &&
                employeeBaseResponse.getData() != null &&
                !employeeBaseResponse.getData().isEmpty()) {
            // Assuming you want to convert `employeeBaseResponse.getData()` to ArrayList
            return new ArrayList<>(employeeBaseResponse.getData());
        }
        // Return an empty list if the conditions are not met
        return new ArrayList<>();
    }


    public List<EmployeeDataResponse> mapApiResponseToEmployeeDataResponseList(EmployeeBaseResponse employeeBaseResponse) {
        ArrayList<EmployeeBaseResponse.EmployeeDataApiResponse> responseList = updateBaseResponse(employeeBaseResponse);
        List<EmployeeDataResponse> response = new ArrayList<>();
        for (EmployeeBaseResponse.EmployeeDataApiResponse apiResponse : responseList) {
            EmployeeDataResponse dataResponse = EmployeeDataResponse.builder()
                    .employeeName(apiResponse.getEmployeeName())
                    .employeeAge(apiResponse.getEmployeeAge())
                    .employeeSalary(apiResponse.getEmployeeSalary())
                    .emailId(apiResponse.getEmailId())
                    .build();
            response.add(dataResponse);
        }
    return response;
}

    public EmployeeBaseResponse.EmployeeDataApiResponse convertToDto(EmployeeEntity employeeEntity) {
        EmployeeBaseResponse.EmployeeDataApiResponse dto = new EmployeeBaseResponse.EmployeeDataApiResponse();
        dto.setEmployeeName(employeeEntity.getEmployeeName());
        dto.setEmployeeAge(employeeEntity.getEmployeeAge());
        dto.setEmployeeSalary(employeeEntity.getEmployeeSalary());
        return dto;
    }





    public EmployeeEntity convertToEntity(EmployeeBaseResponse.EmployeeDataApiResponse employeeDataApiResponse) {
        EmployeeEntity entity = new EmployeeEntity();
//        // If empid is zero or not set, let the database generate it
//        if (employeeDataResponse.getEmpId() != 0) {
//            entity.setEmpId(employeeDataResponse.getEmpId());
//        }
        entity.setEmployeeName(employeeDataApiResponse.getEmployeeName());
        entity.setEmployeeAge(employeeDataApiResponse.getEmployeeAge());
        entity.setEmployeeSalary(employeeDataApiResponse.getEmployeeSalary());
        entity.setEmailId(employeeDataApiResponse.getEmailId());
        return entity;
    }

    // New methods to handle lists

    public List<EmployeeBaseResponse.EmployeeDataApiResponse> convertToDtoList(List<EmployeeEntity> employeeEntities) {
        return employeeEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<EmployeeEntity> convertToEntityList(List<EmployeeBaseResponse.EmployeeDataApiResponse> employeeDataApiRespons) {
        return employeeDataApiRespons.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }
}