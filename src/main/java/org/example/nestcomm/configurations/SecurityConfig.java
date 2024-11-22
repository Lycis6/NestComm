package org.example.nestcomm.configurations;

import org.example.nestcomm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity

public class SecurityConfig {
    @Autowired
    UserService userService;

    @Bean
    public UserDetailsService UserDetailsService() {
        return userService;
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("user/registration/new", "product", "/image/{id}",
                                "product/{id}", "/product/find", "errors").permitAll()
                        .requestMatchers("/home","/user/update","user/passwordChange","user/passwordChange/new",
                                "passwordChange","login-success").authenticated()
                        .requestMatchers("/product/delete/{id}","/product/create/new",
                                "/user/became/author", "/author/{email}", "product/create", "productCreate").authenticated()
                        .requestMatchers("admin","/admin/ban/{email}", "/admin/unban/{email}",
                                "admin/user/{email}").hasAuthority("ADMIN")
                        .requestMatchers( "/static/**").permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/login-success",true)
                        .failureUrl("/login-error")
                        .permitAll()
                )
                .logout(logout -> logout.logoutUrl("/logout")
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .permitAll()
                );


        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(UserDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
