package com.cws.shop.config;

import com.cws.shop.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import java.util.Arrays;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}

	@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/register", "/api/login", "/api/forgot-password",
                        "/api/reset-password", "/api/verify", "/api/products/**",
                        "/api/users/me", "/api/notifications/**").permitAll()
                .requestMatchers("/superadmin/**").hasRole("SUPER_ADMIN")
                .requestMatchers("/admin/**").hasAnyRole("ADMIN","SUPER_ADMIN")
                .requestMatchers("/seller/**").hasRole("SELLER")
                .requestMatchers("/buyer/**").hasRole("BUYER")
                .requestMatchers("/api/order/**").hasRole("BUYER")
                .requestMatchers("/api/products/**").permitAll()
                .requestMatchers("/api/cart/**").permitAll()
                .anyRequest().authenticated()
        );

    http.addFilterBefore(jwtAuthenticationFilter,
            UsernamePasswordAuthenticationFilter.class);

    return http.build();
}

@Bean
public CorsConfigurationSource corsConfigurationSource() {

    CorsConfiguration configuration = new CorsConfiguration();

    configuration.setAllowedOrigins(Arrays.asList(
        "https://victorious-wave-0c1a24f007.7.azurestaticapps.net"
    ));

    configuration.setAllowedMethods(Arrays.asList(
        "GET",
        "POST",
        "PUT",
        "DELETE",
        "OPTIONS"
    ));

    configuration.setAllowedHeaders(Arrays.asList("*"));
    configuration.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source =
            new UrlBasedCorsConfigurationSource();

    source.registerCorsConfiguration("/**", configuration);

    return source;
}
	/*@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

	http
    .cors(cors -> {})
    .csrf(csrf -> csrf.disable())
    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    .authorizeHttpRequests(auth -> auth

        // PUBLIC APIs
        .requestMatchers(
            "/api/register",
            "/api/login",
            "/api/forgot-password",
            "/api/reset-password",
            "/api/verify",
            "/api/products/**"
        ).permitAll()

        // BUYER APIs
        .requestMatchers("/api/cart/**").hasRole("BUYER")
        .requestMatchers("/api/order/**").hasRole("BUYER")
        .requestMatchers("/api/notifications/**").hasRole("BUYER")

        // ROLE BASED
        .requestMatchers("/admin/**").hasAnyRole("ADMIN","SUPER_ADMIN")
        .requestMatchers("/seller/**").hasRole("SELLER")
        .requestMatchers("/superadmin/**").hasRole("SUPER_ADMIN")

        .anyRequest().authenticated()
    );

	    http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}*/

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
