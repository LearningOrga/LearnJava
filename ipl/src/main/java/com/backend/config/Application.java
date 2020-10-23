package com.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.util.Arrays;
 
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com")
@EntityScan(basePackages = "com.backend")
//@EnableConfigServer
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableCaching(proxyTargetClass = true)
@PropertySource(value = { "classpath:application-${spring.profiles.active}.properties" })
public class Application extends SpringBootServletInitializer {
	

    @Autowired
    private CacheManager cacheManager;

    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

 
    public static void main(String[] args) throws Throwable {

        SpringApplication.run(Application.class, args);
    }
    
    
    @Bean
    public CacheManager cacheManager() {
            SimpleCacheManager result = new SimpleCacheManager();
            result.setCaches(Arrays.asList(new ConcurrentMapCache("match"),new ConcurrentMapCache("user"),new ConcurrentMapCache("matchResult"),new ConcurrentMapCache("playResult"),new ConcurrentMapCache("predict"),new ConcurrentMapCache("rule")));
            
            return result;
        }
    
    
    
}
    
   