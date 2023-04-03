package com.babatunde.streaemmock;

import com.babatunde.streaemmock.service.ProductService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StreaemMockApplication {

    @Autowired
    private ProductService productService;

    public static void main(String[] args) {
        SpringApplication.run(StreaemMockApplication.class, args);
    }

    @PostConstruct
    public void init() {
        productService.init();
    }

}
