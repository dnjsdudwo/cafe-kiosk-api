package com.onandon.cafe.cafekioskapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling
@SpringBootApplication
public class CafeKioskApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CafeKioskApiApplication.class, args);
    }

}
