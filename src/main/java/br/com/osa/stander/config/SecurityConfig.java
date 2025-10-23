package br.com.osa.stander.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class SecurityConfig {

    @Value("${security.bearer.token}")
    private String validToken;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated());
        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));

        http.addFilterBefore(new BearerTokenFilter(validToken), org.springframework.security.web.authentication.AnonymousAuthenticationFilter.class);
        return http.build();
    }

    static class BearerTokenFilter extends OncePerRequestFilter {
        private final String token;

        BearerTokenFilter(String token) {
            this.token = token;
        }

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
                throws ServletException, IOException {
            String auth = request.getHeader("Authorization");
            if (auth != null && auth.startsWith("Bearer ")) {
                String provided = auth.substring(7);
                if (provided.equals(token)) {
                    chain.doFilter(request, response);
                    return;
                }
            }
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"Unauthorized: provide Authorization: Bearer desafio-token\"}");
        }
    }
}
