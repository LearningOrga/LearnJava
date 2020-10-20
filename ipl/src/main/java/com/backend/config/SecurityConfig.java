package com.backend.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Autowired
	private Environment environment;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth)
			throws Exception {

		auth.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery(
						"select LOGIN_NAME,LOGIN_PASS, ENABLED from user_master where LOGIN_NAME=?")
				.authoritiesByUsernameQuery(
						"select LOGIN_NAME, LOGIN_ROLE from user_master where LOGIN_NAME=?").passwordEncoder(passwordEncoder());
	}


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/", "/home").permitAll()
				.antMatchers("/user/all").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/playResult/deleteDuplicate")
				.access("hasRole('ROLE_ADMIN')").antMatchers("/dba/**")
				.access("hasRole('ROLE_ADMIN') and hasRole('DBA')");

		http.authorizeRequests().anyRequest().authenticated().and().formLogin()
				.loginPage("/login").permitAll();


		http.formLogin().defaultSuccessUrl("/ipl_home").and()
				.exceptionHandling().accessDeniedPage("/Access_Denied").and()
				.formLogin().loginPage("/login").failureUrl("/login?error")
				.usernameParameter("username").passwordParameter("password")
				.and().csrf().csrfTokenRepository(csrfTokenRepository()).and()
				.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
				.logout().logoutSuccessUrl("/login?logout");


		String[] activeProfiles = environment.getActiveProfiles();
		String active = activeProfiles[0];
		if(active.equals("inmem")) {
			http.authorizeRequests().antMatchers("/console/*","/console/**").permitAll();
			http.csrf().disable();
			http.headers().frameOptions().disable();
		}
		/*http.sessionManagement().maximumSessions(1)
				.expiredUrl("/login?expired").maxSessionsPreventsLogin(true)
				.and().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
				.invalidSessionUrl("/"); */

		// TODO http.authorizeRequests().anyRequest().authenticated();

	}

	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}
}