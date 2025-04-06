package com.siva.graphql.data.providers;

import com.siva.graphql.data.Employee;
import com.siva.graphql.util.IdGenerator;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
@Component
public class EmployeeDataProvider {

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    private static final List<Employee> employees = new ArrayList<>();

    static {
        Employee employee1 = new Employee("100", "Jeeva",  "Programmer");
        employee1.setAddressId("A1");
        employee1.setDepartmentId("D1");
        Employee employee2 = new Employee("101", "Siva",  "Programmer");
        employee2.setAddressId("A2");
        employee2.setDepartmentId("D1");
        Employee employee3 = new Employee("102", "Dieva",  "CA");
        employee3.setAddressId("A3");
        employee3.setDepartmentId("D3");
        Employee employee4 = new Employee("103", "Suja", "HR Recruiter");
        employee4.setAddressId("A4");
        employee4.setDepartmentId("D2");
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);
    }


    public Employee addEmployee(String name, String role) {
        writeLock.lock();
        try {
            Employee employee =new Employee(IdGenerator.generateEmployeeId(),
                    name, role);
            employees.add(employee);
            return employee;
        } finally {
            writeLock.unlock();
        }
    }

    public Employee updateEmployeeAddress(String id, String addressId){
        Employee employee = getEmployeeById(id);
        employee.setAddressId(addressId);
        return employee;
    }

    public Employee updateEmployeeDepartment(String id, String departmentId){
        Employee employee = getEmployeeById(id);
        employee.setDepartmentId(departmentId);
        return employee;
    }

    public Employee getEmployeeById(@Nonnull String id) {
        readLock.lock();
        try {
           return  employees.stream().filter(employee -> id.equals(employee.getId()))
                    .findFirst().orElseThrow(() -> new IllegalArgumentException("Not a valid Id"));
        } finally {
            readLock.unlock();
        }
    }


}
