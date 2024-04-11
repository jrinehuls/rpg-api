package com.jrinehuls.rpgapi.configuration;

import com.jrinehuls.rpgapi.security.SecurityConstants;
import com.jrinehuls.rpgapi.security.filter.AuthenticationFilter;
import com.jrinehuls.rpgapi.security.filter.ExceptionHandlerFilter;
import com.jrinehuls.rpgapi.security.filter.JWTAuthorizationFilter;
import com.jrinehuls.rpgapi.security.manager.CustomAuthenticationManager;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationManager authenticationManager;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager);
        authenticationFilter.setFilterProcessesUrl(SecurityConstants.AUTHENTICATION_PATH);

        http
                .headers(headers -> headers.frameOptions(options -> options.disable())) // Don't need when not using H2
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(antMatcher("/h2/**")).permitAll() // Don't need when not using H2
                        .requestMatchers(antMatcher(HttpMethod.POST, "/api/user/register")).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
                .addFilter(authenticationFilter)
                .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class)
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

}
