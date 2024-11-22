package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/public/**").permitAll() // Permitir rutas públicas
                        .anyRequest().authenticated() // Cualquier otra ruta requiere autenticación
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login") // Página personalizada para el login
                        .defaultSuccessUrl("/profile", true) // Redirige al perfil después del login
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID", "_ga", "_ga_R7BNYD4FEK", "_sp_id.1fff") // Borra cookies adicionales

                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Crea sesiones solo si es necesario
                )
                .csrf(csrf -> csrf.disable()); // Deshabilita CSRF para simplificar pruebas locales (no recomendado para producción)

        return http.build();
    }
}
