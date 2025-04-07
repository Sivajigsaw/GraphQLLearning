package com.siva.graphql;

import com.siva.graphql.controller.EmployeeController;
import com.siva.graphql.data.Employee;
import com.siva.graphql.data.providers.AddressDataProvider;
import com.siva.graphql.data.providers.DepartmentDataProvider;
import com.siva.graphql.data.providers.EmployeeDataProvider;
import com.siva.graphql.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.ContextConfiguration;

@GraphQlTest(EmployeeController.class)
@ContextConfiguration(classes= {EmployeeController.class, EmployeeService.class, EmployeeDataProvider.class, AddressDataProvider.class, DepartmentDataProvider.class})
public class EmployeeControllerTest {
    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void testEmployeeById(){
        // Document Name should match the name in the graphql-test file i.e. getEmployeeDetails.graphql
        Employee employee = graphQlTester.documentName("getEmployeeDetails")
                .variable("id", "100")
                .execute().path("employeeById").entity(Employee.class).get();
        Assertions.assertEquals("Jeeva", employee.getName());
    }
}
