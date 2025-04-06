package com.siva.graphql.controller;

import com.siva.graphql.data.Employee;
import com.siva.graphql.data.providers.ProjectDataProvider;
import com.siva.graphql.service.EmployeeService;
import com.siva.graphql.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@SchemaMapping(typeName = "Project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final EmployeeService employeeService;

    @QueryMapping
    public ProjectDataProvider.Project getProjectDetailsById(@Argument String id) {
        return projectService.getProjectById(id);
    }

    @SchemaMapping(field = "members")
    public List<Employee> getMembers(ProjectDataProvider.Project project) {
        return project.employeeIds().stream().map(employeeService::getEmployeeById).toList();
    }


}
