package com.example.it210_projectfinal.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception
    ) throws IOException, ServletException {
        String username = request.getParameter("username");
        String error;
        if (exception instanceof UsernameNotFoundException) {
            error = "username";
        } else if (exception instanceof BadCredentialsException) {
            error = "password";
        } else {
            error = "unknown";
        }
        response.sendRedirect(
                "/auth/login?error=" + error
                        + "&username=" + username
        );
    }
}