package com.isa.pad.sunglasseswarehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Faust on 12/18/2017.
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.isa.pad.sunglasseswarehouse")
public class SpringBootMainApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMainApp.class, args);
    }
}
