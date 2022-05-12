package com.github.majidshoorabi.security.config;

import com.github.majidshoorabi.security.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * @author majid.shoorabi
 * @created 2022-05-May
 * @project peysaz
 */


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/login", "/h2").permitAll()     // pages that don't need to authentication - you must add your login page here
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")    // introduce your login page
                .usernameParameter("email")         // change login username parameter
                //.defaultSuccessUrl("/admin", true);              // after login success go to this url
                .successHandler(new SuccessLoginHandler());        // after login redirect to page with this handler


        // for display h2 console you should add this two lines
        http.headers().frameOptions().disable();
        // http.csrf().disable(); this added in above configuration
    }


    /**
     * JDBC Authentication
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userService);
    }


    /**
     * For custom in memoryAuthentication you need a passwordEncoder
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();       // Password is plain text
    }
}