package com.jrinehuls.rpgapi.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrinehuls.rpgapi.dto.user.UserDto;
import com.jrinehuls.rpgapi.exception.badrequest.UserBadRequestException;
import com.jrinehuls.rpgapi.security.SecurityConstants;
import com.jrinehuls.rpgapi.security.manager.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

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
            throw new UserBadRequestException("{\"message\": \"User needs a username and password fields\"}");
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
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("{\"message\": " + "\"" + failed.getMessage() + "\"}");
        response.getWriter().flush();
    }

}
