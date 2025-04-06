package com.siva.graphql.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Employee {

    private final String id;
    private final String name;
    private String addressId;
    private String departmentId;
    private final String role;
}
