package com.example.tp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfigue extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    /*
        PasswordEncoder passwordEncoder = passwordEncoder();
        String encodePWD = passwordEncoder.encode("1234");

        auth.inMemoryAuthentication()
                .withUser("user1")
                .password(encodePWD)
                .roles("USERS");
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(encodePWD)
                .roles("ADMIN", "USERS");

     */
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("**/admin/**").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("****/user/**").hasAnyRole("USERS");
        http.authorizeRequests().antMatchers("/webjars/**").permitAll();
        //tout les requete http necessite une authentification
        http.authorizeRequests().anyRequest().authenticated();
        http.exceptionHandling().accessDeniedPage("/403");
    }
}
