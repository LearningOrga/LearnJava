package com.backend.config;
 
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
 
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com")
@EntityScan(basePackages = "com.backend")
@EnableConfigServer
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableCaching(proxyTargetClass = true)
public class Application {
	

    @Autowired
    private CacheManager cacheManager;
 
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
    
   