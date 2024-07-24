package com.example.frombasedsecurity.demoformbased.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/apiadmin").hasRole("ADMIN")
                .requestMatchers("/apiuser").hasRole("USER")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form.permitAll())
            .logout(lg -> lg.permitAll());

        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
            .username("iam")
            .password(passwordEncoder().encode("abc"))
            .roles("USER")
            .build();
        
        UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder().encode("admin"))
            .roles("ADMIN")
            .build();
        
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
/*@Bean tells Spring that this method returns a bean to be managed by the Spring container.
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception is a method that configures the security filter chain. 
It takes an HttpSecurity object as a parameter to configure security.
httpSecurity is used to configure web-based security for specific HTTP requests.
authorizeHttpRequests configures authorization for HTTP requests.
auth -> auth is a lambda expression that simplifies the configuration.
requestMatchers("/apiadmin").hasRole("ADMIN") allows only users with the ADMIN role to access /apiadmin.
requestMatchers("/apiuser").hasRole("USER") allows only users with the USER role to access /apiuser.
anyRequest().authenticated() ensures that any other request requires authentication. 
formLogin(form -> form.permitAll()) enables form-based login and allows access to the login page for all users.
logout(lg -> lg.permitAll()) allows all users to access the logout functionality.
return httpSecurity.build(); builds and returns the SecurityFilterChain object.
@Bean tells Spring that this method returns a bean to be managed by the Spring container.
public UserDetailsService userDetailsService() is a method that creates an in-memory user details service.
UserDetails user = User.builder() creates a user with the username iam and password abc (encoded using BCryptPasswordEncoder). The user is assigned the role USER.
UserDetails admin = User.builder() creates a user with the username admin and password admin (encoded using BCryptPasswordEncoder). The user is assigned the role ADMIN.
return new InMemoryUserDetailsManager(user, admin); returns an InMemoryUserDetailsManager initialized with the user and admin details. This manager will be used to handle user authentication in memory.
@Bean tells Spring that this method returns a bean to be managed by the Spring container.
public PasswordEncoder passwordEncoder() is a method that creates a BCryptPasswordEncoder.
return new BCryptPasswordEncoder(); returns a new instance of BCryptPasswordEncoder, which will be used to encode passwords securely.*/