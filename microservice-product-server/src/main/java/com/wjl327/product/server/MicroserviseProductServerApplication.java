package com.wjl327.product.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroserviseProductServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviseProductServerApplication.class, args);
    }

}
