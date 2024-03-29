package com.example.tp.security;

import com.example.tp.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfigue extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

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

        auth.userDetailsService(userDetailsService);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("**/admin/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers("****/user/**").hasAuthority("USERS");
        http.authorizeRequests().antMatchers("/webjars/**").permitAll();
        http.authorizeRequests().antMatchers("/css/**").permitAll();
        http.authorizeRequests().antMatchers("/Images/**").permitAll();
        http.authorizeRequests().antMatchers("/js/**").permitAll();
        //tout les requete http necessite une authentification
        http.authorizeRequests().anyRequest().authenticated().and()
                .formLogin()
                .loginPage("/login")
                .permitAll().and().logout().invalidateHttpSession(true).clearAuthentication(true).
                logoutRequestMatcher(new AntPathRequestMatcher("/logout")).
                logoutSuccessUrl("/logout?logout").permitAll();

        http.exceptionHandling().accessDeniedPage("/403");
    }
}
