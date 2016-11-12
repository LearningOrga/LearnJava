package com.ipl.config;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
 
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com")
@EntityScan(basePackages = "com")
public class Application {
 
    public static void main(String[] args) throws Throwable {
        SpringApplication.run(Application.class, args);
    }
}