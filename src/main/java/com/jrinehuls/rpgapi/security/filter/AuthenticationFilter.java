package com.jrinehuls.rpgapi.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrinehuls.rpgapi.dto.user.UserDto;
import com.jrinehuls.rpgapi.exception.badrequest.UserBadRequestException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    // Gets invoked by requests on api/user/authenticate similar to @RequestMapping() on a controller.
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserDto userDto = new ObjectMapper().readValue(request.getInputStream(), UserDto.class);
            System.out.println(userDto);
        } catch (IOException e) {
            throw new UserBadRequestException("{\"message\": \"User needs a username and password fields\"}");
        }
        return super.attemptAuthentication(request, response);
    }
}
