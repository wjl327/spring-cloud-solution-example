package com.wlj327.product.homepage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProductHomepageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductHomepageApplication.class, args);
    }

}
