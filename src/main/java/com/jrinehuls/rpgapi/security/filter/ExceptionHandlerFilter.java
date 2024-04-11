package com.jrinehuls.rpgapi.security.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.jrinehuls.rpgapi.exception.badrequest.UserBadRequestException;
import com.jrinehuls.rpgapi.exception.notfound.UserNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

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
            response.getWriter().write("{\"message\": " + "\"" + e.getMessage() + "\"}");
            response.getWriter().flush();
        } catch (JWTVerificationException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            // Could make object and have jackson write string
            response.getWriter().write("Invalid JWT" + e.getMessage());
            response.getWriter().flush();
        }
    }
}
