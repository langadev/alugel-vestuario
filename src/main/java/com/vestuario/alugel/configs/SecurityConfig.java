package com.vestuario.alugel.configs;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {
   @Autowired
   private UserDetailsService userDetailsService;

     @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .httpBasic(Customizer.withDefaults())
        .formLogin(Customizer.withDefaults())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize -> authorize
            // Permitir acesso ao Swagger
            .requestMatchers(
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/swagger-resources/**",
                "/webjars/**"
            ).permitAll()
            // Seus endpoints públicos
            .requestMatchers("/register", "/login").permitAll()
            // Apenas ADMIN acessa esses endpoints
            .requestMatchers("/utilizadores/**").hasRole("ADMIN")
            // Qualquer outro endpoint requer autenticação
            .anyRequest().authenticated()
        );

    return http.build();
}

    
// @Bean
//     public UserDetailsService userDetailsService2(){
//         UserDetails user1 = User.withDefaultPasswordEncoder()
//         .username("langa")
//         .password("123")
//         .roles("ADMIN")
//         .build();
//         return new InMemoryUserDetailsManager(user1);
//     }

@Bean
public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}

 @Bean
public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(new BCryptPasswordEncoder()); // 🔐 IMPORTANTE
    provider.setUserDetailsService(userDetailsService);
    return provider;
}
    
}
