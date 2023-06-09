package com.example.demotestback;

import com.example.demotestback.services.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoTestBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoTestBackApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductService productService){
        return args -> {
            productService.loadData();
        };
    }
}
