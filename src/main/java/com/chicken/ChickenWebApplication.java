package com.chicken;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableScheduling
public class ChickenWebApplication
{
    public static void main(String[] args) {
        SpringApplication.run(ChickenWebApplication.class, args);
    }
}
