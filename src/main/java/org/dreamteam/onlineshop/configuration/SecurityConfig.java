package org.dreamteam.onlineshop.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    //TODO: dorobit config, ze aky user sa bude napajat na ake endpointy
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain httpSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests.requestMatchers(
                "/swagger-ui/**").permitAll()
                 //.requestMatchers("/**").permitAll()
                .requestMatchers("/login/").permitAll()
                .requestMatchers("/logout/").permitAll()
                .anyRequest().hasRole("USER"))
                .formLogin(formLogin -> formLogin.defaultSuccessUrl("/", true))
                .logout(logout -> logout.logoutSuccessUrl("/login/logout"));
        return http.build();
    }
}
