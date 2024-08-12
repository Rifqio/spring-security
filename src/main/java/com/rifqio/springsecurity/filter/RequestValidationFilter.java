package com.rifqio.springsecurity.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class RequestValidationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String authHeader = ((HttpServletRequest) request).getHeader("Authorization");

        if (authHeader != null && authHeader.trim().startsWith("Basic ")) {
            try {
                String token = new String(Base64.getDecoder().decode(authHeader.trim().substring(6)), StandardCharsets.UTF_8);
                String email = token.split(":")[0];

                if (email.toLowerCase().contains("admin")) {
                    res.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    return;
                }
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                throw new BadCredentialsException("Invalid or failed to decode basic authentication token", e);
            }
        }
        chain.doFilter(request, response);
    }
}
