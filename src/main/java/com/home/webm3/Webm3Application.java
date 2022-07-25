package com.home.webm3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class Webm3Application {

    public static void main(String[] args) {
        SpringApplication.run(Webm3Application.class, args);
    }

}
