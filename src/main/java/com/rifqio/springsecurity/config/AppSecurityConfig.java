package com.rifqio.springsecurity.config;

import com.rifqio.springsecurity.config.filter.CsrfCookieFilter;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class AppSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(@NotNull HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        // For https
        // http.requiresChannel(channel -> channel.anyRequest().requiresSecure());

        http.securityContext(context -> context.requireExplicitSave(false));

        http.sessionManagement(e -> e
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
        );

        http.csrf(config -> {
            config.ignoringRequestMatchers(new AntPathRequestMatcher("/api/v1/auth/**"));
            config.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
            config.csrfTokenRequestHandler(csrfTokenRequestAttributeHandler);
        });


        http.addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class);

        http.cors(cors -> {
            cors.configurationSource(request -> {
                CorsConfiguration config = new CorsConfiguration();
                config.addAllowedOrigin("*");
                config.addAllowedMethod("*");
                config.setAllowCredentials(true);
                config.addAllowedHeader("*");
                return config;
            });
        });

        http.authorizeHttpRequests((requests) -> {
            requests.requestMatchers(new AntPathRequestMatcher("/api/v1/auth/**")).permitAll();
            requests.requestMatchers(new AntPathRequestMatcher("/api/v1/notice/**")).permitAll();
            requests.requestMatchers(new AntPathRequestMatcher("/api/v1/account/**")).hasAuthority("VIEW_ACCOUNT");
            requests.requestMatchers(new AntPathRequestMatcher("/api/v1/balance/**")).hasAuthority("VIEW_BALANCE");
            requests.requestMatchers(new AntPathRequestMatcher("/api/v1/loans/**")).hasAuthority("VIEW_LOANS");
            requests.requestMatchers(new AntPathRequestMatcher("/api/v1/cards/**")).hasAuthority("VIEW_CARDS");
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