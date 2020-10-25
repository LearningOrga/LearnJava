package com.backend.config;

import java.util.Properties;

import javax.sql.DataSource;

import liquibase.integration.spring.SpringLiquibase;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.backend" })
@EnableJpaAuditing
public class HibernateConfiguration {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Environment environment;

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		String[] activeProfiles = environment.getActiveProfiles();
		String active = activeProfiles[0];
		if(active.equals("local") || active.equals("aws")) {
			sessionFactory.setDataSource(dataSource());
		}
		else{
			sessionFactory.setDataSource(dataSourceInMem());
		}
		sessionFactory.setPackagesToScan(new String[] { "com.backend.model" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	@Bean
	@Profile({"local","aws"})
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(environment
				.getRequiredProperty("jdbc.driverClassName"));

		//String jdbcUrl = environment.getRequiredProperty("JDBC_CONNECTION_STRING"); for aws
		//logger.debug("Getting remote connection with connection string from environment variables."+jdbcUrl);
		String jdbcUrl = environment.getRequiredProperty("jdbc.url");
		dataSource.setUrl(jdbcUrl);
       	return dataSource;
	}


	//@Bean
	//@Profile("inmem")
	/*public DataSource dataSourceInMem() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment
				.getRequiredProperty("jdbc.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
		dataSource.setUsername("sa");
		//dataSource.setPassword("sa");

		return dataSource;
	} */


    @Bean
    @Profile("inmem")
    public DataSource dataSourceInMem() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //dataSource.setDriverClassName(environment
          //''      .getRequiredProperty("jdbc.driverClassName"));
        //dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testdb");
        dataSource.setUsername("sa");
        //dataSource.setPassword("sa");

        return dataSource;
    }

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect",
				environment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql",
				environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql",
				environment.getRequiredProperty("hibernate.format_sql"));
		properties.put("hibernate.hbm2ddl.auto",
				environment.getRequiredProperty("hibernate.hbm2ddl.auto"));	
				
		
		return properties;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory s) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}

}
