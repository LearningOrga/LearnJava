package com.ipl.config;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
 
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com")
@EntityScan(basePackages = "com")
@EnableConfigServer
@EnableGlobalMethodSecurity(securedEnabled = true)
public class Application {
 
    public static void main(String[] args) throws Throwable {
        SpringApplication.run(Application.class, args);
    }
}
    
   