package com.org.emp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "empdetails", schema = "employee")
public class EmployeeEntity {

    @Id
    @SequenceGenerator(
            name = "empdata_seq",            // Name of the sequence generator
            sequenceName = "empdata_seq",    // Name of the database sequence
            allocationSize = 1             // Increment by 1 each time
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    private Long empId;

    @Column(name = "emp_name")
    private String employeeName;

    @Column(name = "emp_age")
    private int employeeAge;

    @Column(name = "emp_salary")
    private Double employeeSalary;

    //add new column in db
    @Column(name = "email_id")
    private String emailId;

}
