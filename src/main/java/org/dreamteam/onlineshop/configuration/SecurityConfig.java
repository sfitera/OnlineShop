package org.dreamteam.onlineshop.configuration;

import org.dreamteam.onlineshop.service.UserService;
import org.dreamteam.onlineshop.service.UserServiceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    //TODO: dorobit config, ze aky user sa bude napajat na ake endpointy
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @Bean
//    public SecurityFilterChain httpSecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
//                        .requestMatchers("/swagger-ui/**").permitAll()
//                        .requestMatchers("/**").permitAll()
//                        .requestMatchers("/api/orderitem/add/**").permitAll()
//                        .requestMatchers("/login/").permitAll()
//                        .requestMatchers("/logout/").permitAll()
//                        .anyRequest().hasRole("USER"))
//                .formLogin(formLogin -> formLogin.defaultSuccessUrl("/", true))
//                .logout(logout -> logout.logoutSuccessUrl("/login/logout"));
//        return http.build();
//    }
//@Bean
//public SecurityFilterChain httpSecurityFilterChain(HttpSecurity http) throws Exception {
//    http
//            .csrf(AbstractHttpConfigurer::disable) // Moderná syntax na vypnutie CSRF
//            .authorizeHttpRequests(auth -> auth
//                    .anyRequest().permitAll()); // Povolenie prístupu pre všetky požiadavky bez autentifikácie
//    return http.build();
//}
    private final UserServiceBean userServiceBean;
    private final JwtUtils jwtUtils;

    public SecurityConfig(@Lazy UserServiceBean userServiceBean, JwtUtils jwtUtils) {
        this.userServiceBean = userServiceBean;
        this.jwtUtils = jwtUtils;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userServiceBean);
        authProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(authProvider);
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter(jwtUtils, userServiceBean);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/**").permitAll()
                        .requestMatchers("/api/users/**").authenticated()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
