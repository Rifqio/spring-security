package com.rifqio.springsecurity.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
public class AppSecurityConfig {
    /* @Value("${spring.security.oauth2.resourceserver.opaquetoken.introspection-uri}")
    private String introspectionUri;

    @Value("${spring.security.oauth2.resourceserver.opaquetoken.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.resourceserver.opaquetoken.client-secret}")
    private String clientSecret;*/

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(@NotNull HttpSecurity http) throws Exception {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());

        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement(e -> e.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.cors(cors -> cors.configurationSource(request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.addAllowedOrigin("*");
            config.addAllowedMethod("*");
            config.setAllowCredentials(true);
            config.addAllowedHeader("*");
            return config;
        }));

        http.oauth2ResourceServer(resource -> resource.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)));
        /* http.oauth2ResourceServer(resource -> resource.opaqueToken(opaque -> opaque
                .authenticationConverter(new KeycloakOpaqueRoleConverter())
                .introspectionUri(introspectionUri)
                .introspectionClientCredentials(this.clientId, this.clientSecret)));*/

        http.authorizeHttpRequests(requests -> {
            requests.requestMatchers("/api/v1/auth/register", "/api/v1/notice/**").permitAll();

            requests.requestMatchers("/api/v1/notice/**").permitAll();
            // requests.requestMatchers("/api/v1/account/**").hasAuthority("VIEW_ACCOUNT");
            // requests.requestMatchers("/api/v1/balance/**").hasAuthority("VIEW_BALANCE");
            // requests.requestMatchers("/api/v1/loans/**").hasAuthority("VIEW_LOANS");
            requests.requestMatchers("/api/v1/account/**").hasAnyRole("ADMIN", "USER");
            // requests.requestMatchers("/api/v1/account/**").authenticated();

            requests.requestMatchers("/api/v1/balance/**", "/api/v1/loan/**", "/api/v1/card/**").hasRole("USER");
            requests.anyRequest().authenticated();
        });
        return http.build();
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}