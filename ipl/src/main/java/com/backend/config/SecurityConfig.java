package com.backend.config;

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

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    private Environment environment;




    // In-memory authentication to authenticate the user i.e. the user credentials are stored in the memory.
    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("Jitendra1").password("guest1234").roles("USER");
        auth.inMemoryAuthentication().withUser("Jitendra").password("{noop}nicetrykeepitup").roles("ADMIN");
    }*/


    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth)
            throws Exception {

        System.out.print("DB USED - " + dataSource.toString());



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

        http.authorizeRequests()
                .antMatchers( "/","/home","/setup/**","/console/*","/console/**").permitAll()
                .antMatchers("/user/all").hasAnyRole("ADMIN")
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                /*.antMatchers("/playResult/deleteDuplicate").hasAnyRole("ROLE_ADMIN")
                .antMatchers("/dba/**").hasAnyRole("DBA")*/
                //.antMatchers("/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated().and().formLogin()
                .loginPage("/login").permitAll()
                .and().logout().permitAll();

        //http.csrf().disable();

        http.formLogin().defaultSuccessUrl("/ipl_home").and()
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