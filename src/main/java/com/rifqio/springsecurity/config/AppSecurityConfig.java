package com.rifqio.springsecurity.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class AppSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(@NotNull HttpSecurity http) throws Exception {
        // For https
        // http.requiresChannel(channel -> channel.anyRequest().requiresSecure());
        http.sessionManagement(e -> e
                .sessionFixation(SessionManagementConfigurer.SessionFixationConfigurer::none)
                .maximumSessions(3)
                .maxSessionsPreventsLogin(true)
        );

        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests((requests) -> {
            requests.requestMatchers(new AntPathRequestMatcher("/api/v1/auth/**")).permitAll();
            requests.requestMatchers(new AntPathRequestMatcher("/api/v1/notice/**")).permitAll();
            requests.anyRequest().authenticated();
        });

        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
//        http.exceptionHandling(e -> e
//                .authenticationEntryPoint(new AuthExceptionHandler())
//                .accessDeniedHandler(new CustomAccessDeniedHandler())
//        );

        return http.build();
    }

    /* @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    } */

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}