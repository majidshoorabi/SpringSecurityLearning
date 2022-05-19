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
                .antMatchers("/", "/login", "/error", "/h2", "/info").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").usernameParameter("email").successHandler(new SuccessLoginHandler())
                .and().oauth2Login()
                .loginPage("/oauthLogin")                           // set a new address for oauth login then doesn't redirect to oauthLogin page when you got to login page, you can open this page or not
                .and().rememberMe()
                .and().exceptionHandling().accessDeniedPage("/error")
                .and().logout().deleteCookies("remember");

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