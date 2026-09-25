package com.example.FirstProject.Service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class EmployeeService {

    List<Employee> employees = new ArrayList<>();

    public Employee saveEmployee(Employee emp) {
        employees.add(emp);
        return emp;
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }
}