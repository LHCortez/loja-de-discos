package com.luiz.lhcdiscos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class LhcdiscosApplication implements WebMvcConfigurer, CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LhcdiscosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
