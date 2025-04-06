package com.siva.graphql.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Random;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdGenerator {
    private static final Random RANDOM = new Random();

    public static String generateAddressId(){
        return String.join("","A", String.valueOf(RANDOM.nextInt(10, Integer.MAX_VALUE )));
    }

    public static String generateEmployeeId(){
        return String.join("","E", String.valueOf(RANDOM.nextInt(10, Integer.MAX_VALUE )));
    }

    public static String generateDepartmentId(){
        return String.join("","D", String.valueOf(RANDOM.nextInt(10, Integer.MAX_VALUE )));
    }
}
