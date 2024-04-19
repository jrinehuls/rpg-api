package com.jrinehuls.rpgapi.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jrinehuls.rpgapi.dto.user.UserDto;
import com.jrinehuls.rpgapi.exception.ErrorResponse;
import com.jrinehuls.rpgapi.exception.badrequest.UserBadRequestException;
import com.jrinehuls.rpgapi.security.SecurityConstants;
import com.jrinehuls.rpgapi.security.manager.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final CustomAuthenticationManager authenticationManager;

    // Gets invoked by requests on api/user/authenticate similar to @RequestMapping() on a controller.
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserDto userDto = new ObjectMapper().readValue(request.getInputStream(), UserDto.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword());
            System.out.println(userDto);
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            Map<String, List<String>> errors = new HashMap<>();
            errors.put("password", List.of("Field property name may not be correct, see key naming"));
            errors.put("username", List.of("Field property name may not be correct, see key naming"));
            throw new UserBadRequestException(buildResponse(errors));
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = JWT.create()
                .withSubject(authResult.getName()) // TODO: add claim or key id for user id
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_LIFE_MILLIS))
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET));
        response.addHeader("Authorization", "Bearer " + token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        // Incorrect password
        Map<String, List<String>> errors = new HashMap<>();
        errors.put("password", List.of(failed.getMessage()));

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(buildResponse(errors));
        response.getWriter().flush();
    }

    private String buildResponse(Map<String, List<String>> errors) {
        ErrorResponse errorResponse = new ErrorResponse(errors);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String json;
        try {
            json = mapper.writeValueAsString(errorResponse);
        } catch (JsonProcessingException e) {
            json = "{\"exception\": \"%s\"}".formatted(e.getMessage());
        }
        return json;
    }

}
