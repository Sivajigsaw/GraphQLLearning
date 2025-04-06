package com.siva.graphql.data.providers;


import com.siva.graphql.util.IdGenerator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static com.siva.graphql.util.IdGenerator.generateDepartmentId;

@Component
public class DepartmentDataProvider {

    public record Department(String id, String name) {
    }


    private static final List<Department> departmentList = new ArrayList<>();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    public static final List<Department> ALL_DEPARTMENT = List.of(new Department("D1", "HR"),
            new Department("D2", "IT"), new Department("D3", "Accounting"));

    static {
        departmentList.addAll(ALL_DEPARTMENT);
    }


    public Department getDepartmentById(String id) {
        readLock.lock();
        try {
            return departmentList.stream().filter(department -> id.equals(department.id))
                    .findFirst().orElseThrow(() -> new IllegalArgumentException("Not a valid department Id"));
        } finally {
            readLock.unlock();
        }
    }

    public Department addDepartment(String name) {
        writeLock.lock();
        try {
            Department department = new Department(generateDepartmentId(), name);
            departmentList.add(department);
            return department;
        } finally {
            writeLock.unlock();
        }
    }
}
