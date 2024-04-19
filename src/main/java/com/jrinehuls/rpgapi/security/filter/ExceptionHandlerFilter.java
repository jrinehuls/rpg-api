package com.jrinehuls.rpgapi.security.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jrinehuls.rpgapi.exception.ErrorResponse;
import com.jrinehuls.rpgapi.exception.badrequest.UserBadRequestException;
import com.jrinehuls.rpgapi.exception.notfound.UserNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (UserBadRequestException e) {
            response.setStatus(e.getStatusCode());
            response.getWriter().write(e.getMessage());
            response.getWriter().flush();
        } catch (UserNotFoundException e) {
            response.setStatus(e.getStatusCode().value());
            response.getWriter().write(handleUserNotFoundException(e));
            response.getWriter().flush();
        } catch (JWTVerificationException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            // Could make object and have jackson write string
            response.getWriter().write(handleJwtVerificationException(e));
            response.getWriter().flush();
        }
    }

    private String handleUserNotFoundException(UserNotFoundException e) {
        Map<String, List<String>> errors = new HashMap<>();
        errors.put("username", List.of(e.getMessage()));
        ErrorResponse errorResponse = new ErrorResponse(errors);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String json;
        try {
            json = mapper.writeValueAsString(errorResponse);
        } catch (JsonProcessingException ex) {
            json = "{\"exception\": \"%s\"}".formatted(ex.getMessage());
        }
        return json;
    }

    private String handleJwtVerificationException(JWTVerificationException e) {
        Map<String, List<String>> errors = new HashMap<>();
        errors.put("authorization", List.of(e.getMessage()));
        ErrorResponse errorResponse = new ErrorResponse(errors);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String json;
        try {
            json = mapper.writeValueAsString(errorResponse);
        } catch (JsonProcessingException ex) {
            json = "{\"exception\": \"%s\"}".formatted(ex.getMessage());
        }
        return json;
    }
}
