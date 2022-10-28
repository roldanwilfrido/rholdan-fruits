package com.rholdan.fruits.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@AllArgsConstructor
@Configuration
public class SecurityConfig {

    private final InternalStoreConfig internalStoreConfig;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/public/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .build();

    }

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {

        return http
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder())
                .and().build();

    }

    @Bean
    UserDetailsService userDetailsService() {

        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        inMemoryUserDetailsManager
                .createUser(
                        User.withUsername(internalStoreConfig.getUsername())
                                .password(internalStoreConfig.getPassword())
                                .roles()
                                .build()
                );
        return inMemoryUserDetailsManager;

    }

    @Bean
    PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

}
