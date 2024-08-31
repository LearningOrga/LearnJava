package com.backend;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.socket.ConnectionSocketFactory;
import org.apache.hc.client5.http.socket.PlainConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.http.URIScheme;
import org.apache.hc.core5.http.config.Registry;
import org.apache.hc.core5.http.config.RegistryBuilder;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.IOException;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Arrays;

@SpringBootApplication(scanBasePackages = "com")
@EnableJpaRepositories(basePackages = "com")
@EntityScan(basePackages = "com.backend")
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableCaching(proxyTargetClass = true)
@PropertySource(value = {"classpath:application-${spring.profiles.active}.properties"})
@ComponentScan({"com.backend"})
public class Application {

    @Value("${server.ssl.key-store}")
    private String jksFile;

    @Value("${server.ssl.key-store-password}")
    private String jksPassword;

    @Autowired
    private CacheManager cacheManager;

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager result = new SimpleCacheManager();
        result.setCaches(Arrays.asList(new ConcurrentMapCache("match"), new ConcurrentMapCache("user"), new ConcurrentMapCache("matchResult"), new ConcurrentMapCache("playResult"), new ConcurrentMapCache("predict"), new ConcurrentMapCache("rule")));

        return result;
    }

    @Bean
    public OpenAPI iplApp() {
        return new OpenAPI()
                .info(new Info()
                        .title("IPL app for Learning")
                        .version("1.0.0")
                        .contact(new Contact().name("Jitendra").email("hirpara.jitendra@gmail.com")));
    }

    @Bean
    public RestTemplate restTemplatewithKeyStore(RestTemplateBuilder builder) throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, UnrecoverableKeyException {

      String jksPassword = "password"; //TODO
        SSLContext sslContext = new SSLContextBuilder()
                .loadKeyMaterial(ResourceUtils.getFile(jksFile),jksPassword.toCharArray(),jksPassword.toCharArray())
                .loadTrustMaterial(ResourceUtils.getFile(jksFile),jksPassword.toCharArray()).build();
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register(URIScheme.HTTP.getId(), new SSLConnectionSocketFactory(sslContext))
                .register(URIScheme.HTTP.getId(), new PlainConnectionSocketFactory()).build();
        HttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(new PoolingHttpClientConnectionManager(socketFactoryRegistry))
                .setConnectionManagerShared(true).build();

        return  builder.requestFactory(()-> new HttpComponentsClientHttpRequestFactory(httpClient)).build();


    }

    @Bean
    RetryTemplate retryTemplate(){
        RetryTemplate retryTemplate = new RetryTemplate();
        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(3000);
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
        simpleRetryPolicy.setMaxAttempts(3);
        retryTemplate.setRetryPolicy(simpleRetryPolicy);
        return retryTemplate;
    }
}
    
   