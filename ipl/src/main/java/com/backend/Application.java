package com.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.util.Arrays;
 
@SpringBootApplication(scanBasePackages = "com")
@EnableJpaRepositories(basePackages = "com")
@EntityScan(basePackages = "com.backend")
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableCaching(proxyTargetClass = true)
@PropertySource(value = { "classpath:application-${spring.profiles.active}.properties" })
@ComponentScan({"com.backend"})
public class Application  {

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
    
   