package com.ipl.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.backend" })
@PropertySource(value = { "classpath:application-local.properties" })
@EnableJpaAuditing
public class HibernateConfiguration {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Environment environment;

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.backend.model" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment
				.getRequiredProperty("jdbc.driverClassName"));

		String jdbcUrl = environment.getRequiredProperty("JDBC_CONNECTION_STRING");


		String dbName = System.getProperty("RDS_DB_NAME");
		String userName = System.getProperty("RDS_USERNAME");
		String password = System.getProperty("RDS_PASSWORD");
		String hostname = System.getProperty("RDS_HOSTNAME");
		String port = System.getProperty("RDS_PORT");

		//String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
		//String jdbcUrl = "jdbc:mysql://" + "aa1jop8mtpj5u1k.cep9jdg4oxmx.us-east-1.rds.amazonaws.com" + ":" + "3306" + "/" + "ebdb" + "?user=" + "ipluser" + "&password=" + "iplmaster";
		logger.debug("Getting remote connection with connection string from environment variables."+jdbcUrl);



		dataSource.setUrl(jdbcUrl);

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
