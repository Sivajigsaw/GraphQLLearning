package com.siva.graphql.controller;

import com.siva.graphql.data.Employee;
import com.siva.graphql.data.providers.AddressDataProvider;
import com.siva.graphql.data.providers.DepartmentDataProvider;
import com.siva.graphql.service.EmployeeService;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
@SchemaMapping(typeName = "Employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @QueryMapping
    public Employee employeeById(@Argument String id) {
        return employeeService.getEmployeeById(id);
    }

    @SchemaMapping(field = "address")
    public AddressDataProvider.Address getAddressById(Employee employee) {
        return employeeService.getAddressById(employee.getAddressId());
    }

    @SchemaMapping(field = "department")
    public DepartmentDataProvider.Department getDepartmentById(Employee employee) {
        return employeeService.getDepartmentById(employee.getDepartmentId());
    }

    @MutationMapping
    public Employee addEmployee(@Argument String name, @Argument String role) {
        return employeeService.addEmployee(name, role);
    }

    @MutationMapping
    public Employee updateEmployeeAddress(@Argument String id, @Argument String addressId) {
        return employeeService.updateEmployeeAddress(id, addressId);
    }

    @MutationMapping
    public Employee updateEmployeeDepartment(@Argument String id, @Argument String departmentId) {
        return employeeService.updateEmployeeDepartment(id, departmentId);
    }

    @MutationMapping
    public AddressDataProvider.Address addAddress(@Argument AddressDataProvider.AddressInput address) { // Argument name should match with the graphql definition otherwise we need to specify
        return employeeService.addAddress(address);
    }

    @MutationMapping
    public DepartmentDataProvider.Department addDepartment(@Argument String name) {
        return employeeService.addDepartment(name);
    }
}
