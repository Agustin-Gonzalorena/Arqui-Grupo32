package com.microservice.gateway.config;

import com.microservice.gateway.security.jwt.JwtFilter;
import com.microservice.gateway.security.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final TokenProvider tokenProvider;

    public SecurityConfig( TokenProvider tokenProvider ) {
        this.tokenProvider = tokenProvider;
    }

    @Bean // hashear la contraseña
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain( final HttpSecurity http ) throws Exception {
        http
                .csrf( AbstractHttpConfigurer::disable );
        http
                .sessionManagement( s -> s.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) );
        http
                .securityMatcher("/api/**" )
                .authorizeHttpRequests( authz -> authz
                        .requestMatchers(HttpMethod.POST, "/api/authenticate").permitAll()
                        .requestMatchers( "/api/parada/cerca/**").hasAuthority( AuthotityConstant._USUARIO )
                        .requestMatchers( HttpMethod.POST,"/api/parada").hasAuthority( AuthotityConstant._ADMIN )
                        .requestMatchers( "/api/parada").permitAll()
                        .requestMatchers( "/api/monopatin/reporte").hasAuthority( AuthotityConstant._MANTENIMIENTO )
                        .requestMatchers( "/api/usuario/ban/**").hasAuthority( AuthotityConstant._ADMIN )
                        .requestMatchers( "/api/monopatin/mantenimiento/**").hasAuthority( AuthotityConstant._MANTENIMIENTO )
                        .requestMatchers( "/api/factura/**").hasAuthority( AuthotityConstant._ADMIN )
                        .requestMatchers( "/api/monopatin/**").hasAuthority( AuthotityConstant._ADMIN )
                        .requestMatchers( "/api/tarifa/**").hasAuthority( AuthotityConstant._ADMIN )
                        .requestMatchers( HttpMethod.POST,"/api/viaje").hasAuthority( AuthotityConstant._USUARIO )
                        .requestMatchers( "/api/viaje/**").hasAuthority( AuthotityConstant._USUARIO )
                        .anyRequest().authenticated()
                )
                .httpBasic( Customizer.withDefaults() )
                .addFilterBefore( new JwtFilter( this.tokenProvider ), UsernamePasswordAuthenticationFilter.class );
        return http.build();
    }

}

