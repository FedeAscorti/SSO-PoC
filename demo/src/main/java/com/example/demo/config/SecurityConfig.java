package com.example.demo.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login").permitAll() // Rutas públicas
                        .anyRequest().authenticated() // Cualquier otra ruta requiere autenticación
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login") // Página personalizada para el login
                        .defaultSuccessUrl("/profile", true) // Redirige después de iniciar sesión
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/") // Redirige después del logout
                );

        return http.build();
    }
}