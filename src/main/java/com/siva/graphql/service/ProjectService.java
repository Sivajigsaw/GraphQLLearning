package com.siva.graphql.service;

import com.siva.graphql.data.providers.ProjectDataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectDataProvider projectDataProvider;

public ProjectDataProvider.Project getProjectById(String id){
    return projectDataProvider.getProjectById(id);
}

}
