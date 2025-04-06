package com.siva.graphql.service;

import com.siva.graphql.data.Employee;
import com.siva.graphql.data.providers.AddressDataProvider;
import com.siva.graphql.data.providers.DepartmentDataProvider;
import com.siva.graphql.data.providers.EmployeeDataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeDataProvider employeeDataProvider;
    private final AddressDataProvider addressDataProvider;
    private final DepartmentDataProvider departmentDataProvider;

    public Employee getEmployeeById(String id) {
        return employeeDataProvider.getEmployeeById(id);
    }

    public DepartmentDataProvider.Department getDepartmentById(String id){
        return departmentDataProvider.getDepartmentById(id);
    }

    public AddressDataProvider.Address getAddressById(String id){
        return addressDataProvider.getAddressById(id);
    }

    public Employee addEmployee(String name, String role){
        return employeeDataProvider.addEmployee(name, role);
    }

    public AddressDataProvider.Address addAddress(AddressDataProvider.AddressInput addressInput){
        return addressDataProvider.addAddress(addressInput);
    }

    public DepartmentDataProvider.Department addDepartment(String name){
        return departmentDataProvider.addDepartment(name);
    }

    public Employee updateEmployeeAddress(String id, String addressId){
        return employeeDataProvider.updateEmployeeAddress(id,addressId);
    }

    public Employee updateEmployeeDepartment(String id, String departmentId){
        return employeeDataProvider.updateEmployeeDepartment(id, departmentId);
    }

}
