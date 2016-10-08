package com.ipl.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.session.SessionManagementFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	/*
	 * @Autowired public void
	 * configureGlobalSecurity(AuthenticationManagerBuilder auth) throws
	 * Exception {
	 * auth.inMemoryAuthentication().withUser("").password("").roles("USER");
	 * auth.inMemoryAuthentication().withUser("").password("").roles("USER");
	 * auth.inMemoryAuthentication().withUser("").password("").roles("USER");
	 * auth.inMemoryAuthentication().withUser("").password("").roles("ADMIN");
	 * auth
	 * .inMemoryAuthentication().withUser("").password("").roles("ADMIN","DBA"
	 * );//dba have two roles. }
	 */

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth)
			throws Exception {

		auth.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery(
						"select LOGIN_NAME,LOGIN_PASS, ENABLED from user_master where LOGIN_NAME=?")
				.authoritiesByUsernameQuery(
						"select LOGIN_NAME, LOGIN_ROLE from user_master where LOGIN_NAME=?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/", "/home").permitAll()
				.antMatchers("/user/all").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/dba/**")
				.access("hasRole('ROLE_ADMIN') and hasRole('DBA')").and()
				.formLogin().defaultSuccessUrl("/ipl_home").and()
				.exceptionHandling().accessDeniedPage("/Access_Denied").and()
				.formLogin().loginPage("/login").failureUrl("/login?error")
				.usernameParameter("username").passwordParameter("password")
				.and().csrf().csrfTokenRepository(csrfTokenRepository()).and()
				.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
				.logout().logoutSuccessUrl("/login?logout");

	}

	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}
}