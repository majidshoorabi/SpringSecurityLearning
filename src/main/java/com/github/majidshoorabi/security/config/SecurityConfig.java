package com.github.majidshoorabi.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author majid.shoorabi
 * @created 2022-05-May
 * @project peysaz
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/login").permitAll()     // pages that don't need to authentication - you must add your login page here
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")    // introduce your login page
                .usernameParameter("email");        // change login username parameter
    }


    /**
     * In memory authentication with custom user and password
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("foo")
                .password("123")
                .roles("USER");         // if we don't define a role it throws org.springframework.beans.factory.BeanCreationException
    }


    /**
     *  For custom in memoryAuthentication you need a passwordEncoder
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();       // Password is plain text
    }
}
