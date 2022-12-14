package com.example.stada_fult_badgers.security;

import com.example.stada_fult_badgers.enteties.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final  JwtFilter jwtFilter;

    public SecurityConfig(UserDetailsService userDetailsService, JwtFilter jwtFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder();}
//

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity  //configure the httpSecurity object we got given.
                .csrf().disable()
                .cors().and()
                .authorizeRequests(auth -> auth
                        .antMatchers("/api/auth/**").permitAll() //.hasRole(Role.CLEANER.toString())
                        .antMatchers("/user/new/**").permitAll()
                        .antMatchers("/bookings/unclaimed/**").hasRole(Role.CLEANER.toString()) //den kritiska raden
                        .anyRequest().authenticated()
                )
                .userDetailsService(userDetailsService)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build(); //build and return the configured httpSecurity object.
    }
}
