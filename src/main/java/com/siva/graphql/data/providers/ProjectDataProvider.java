package com.siva.graphql.data.providers;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class ProjectDataProvider {
    public record Project(String id, String name, List<String> employeeIds){}

    private static final List<Project> projects = new ArrayList<>();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    static{
        List<String> employeeIds = new ArrayList<>();
        employeeIds.add("100");
        employeeIds.add("101");
        Project p1 = new Project("P1", "Project One", employeeIds);
        List<String> employeeIds1 = new ArrayList<>();
        employeeIds1.add("103");
        Project p2 = new Project("P2", "Project One", employeeIds1);
        projects.add(p1);projects.add(p2);
    }

    public Project getProjectById(String id) {
        readLock.lock();
        try {
            return projects.stream().filter(project -> id.equals(project.id))
                    .findFirst().orElseThrow(() -> new IllegalArgumentException("Not a valid project Id"));
        } finally {
            readLock.unlock();
        }
    }

    public Project addProject(Project project) {
        writeLock.lock();
        try {
            projects.add(project);
            return project;
        } finally {
            writeLock.unlock();
        }
    }

}
