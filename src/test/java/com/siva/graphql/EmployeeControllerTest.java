package com.siva.graphql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siva.graphql.controller.EmployeeController;
import com.siva.graphql.data.Employee;
import com.siva.graphql.data.providers.AddressDataProvider;
import com.siva.graphql.data.providers.DepartmentDataProvider;
import com.siva.graphql.data.providers.EmployeeDataProvider;
import com.siva.graphql.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.ContextConfiguration;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@GraphQlTest(EmployeeController.class)
@ContextConfiguration(classes = {EmployeeController.class, EmployeeService.class, EmployeeDataProvider.class, AddressDataProvider.class, DepartmentDataProvider.class})
public class EmployeeControllerTest {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void testEmployeeById() {
        // Document Name should match the name in the graphql-test file i.e. getEmployeeDetails.graphql
        Employee employee = graphQlTester.documentName("getEmployeeDetails")
                .variable("id", "100").operationName("getEmployeeDetails")
                .execute().path("employeeById").entity(Employee.class).get();
        assertEquals("Jeeva", employee.getName());
    }

    @Test
    void testCreateAddress() throws JsonProcessingException {
        // Operation name should match the Operation given in this case mutation createAddress($addressInput: AddressInput) createAddress
        AddressDataProvider.AddressInput addressInput = new AddressDataProvider.AddressInput("Address Line1", "Address Line2", "zip", "city", "state");
        Map<String, Object> addressInputMap = objectMapper
                .convertValue(addressInput, new TypeReference<Map<String, Object>>() {
                });
        AddressDataProvider.Address address = graphQlTester.documentName("getEmployeeDetails")
                .variable("addressInput", addressInputMap).operationName("createAddress")
                .execute().path("addAddress").entity(AddressDataProvider.Address.class).get();
        assertEquals("Address Line1", address.addressLine1());
        assertNotNull(address.id());
    }


}
