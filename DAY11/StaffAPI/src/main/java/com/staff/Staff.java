package com.staff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.staff"})
public class Staff {

    public static void main(String[] args) {
        SpringApplication.run(Staff.class, args);
    }
}