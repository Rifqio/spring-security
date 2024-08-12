package com.rifqio.springsecurity.config;

import com.rifqio.springsecurity.filter.JWTValidatorFilter;
import com.rifqio.springsecurity.service.AppUserDetailsService;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
public class AppSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(@NotNull HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement(e -> e.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.cors(cors -> cors.configurationSource(request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.addAllowedOrigin("*");
            config.addAllowedMethod("*");
            config.setAllowCredentials(true);
            config.setExposedHeaders(List.of("Authorization"));
            config.addAllowedHeader("*");
            return config;
        }));


        http.addFilterBefore(new JWTValidatorFilter(), BasicAuthenticationFilter.class);

        http.authorizeHttpRequests((requests) -> {
            requests.requestMatchers("/api/v1/auth/**", "/api/v1/notice/**").permitAll();
//            requests.requestMatchers("/api/v1/notice/**").permitAll();
//            requests.requestMatchers("/api/v1/account/**").hasAuthority("VIEW_ACCOUNT");
//            requests.requestMatchers("/api/v1/balance/**").hasAuthority("VIEW_BALANCE");
//            requests.requestMatchers("/api/v1/loans/**").hasAuthority("VIEW_LOANS");
            requests.requestMatchers("/api/v1/account/**").hasAnyRole("ADMIN", "USER");
            requests.requestMatchers("/api/v1/balance/**", "/api/v1/loan/**", "/api/v1/card/**").hasRole("USER");
            requests.anyRequest().authenticated();
        });
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }

    @Bean
    public AuthenticationManager authenticationManager(AppUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        AppAuthenticationProvider appAuthenticationProvider = new AppAuthenticationProvider(userDetailsService, passwordEncoder);
        ProviderManager providerManager = new ProviderManager(appAuthenticationProvider);
        providerManager.setEraseCredentialsAfterAuthentication(false);
        return providerManager;
    }
}