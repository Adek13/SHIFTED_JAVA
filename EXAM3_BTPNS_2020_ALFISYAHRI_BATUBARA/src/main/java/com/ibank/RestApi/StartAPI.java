package com.ibank.RestApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.ibank.RestApi"})
public class StartAPI {
    public static void main(String[] args) {
        SpringApplication.run(StartAPI.class, args);
    }
}
