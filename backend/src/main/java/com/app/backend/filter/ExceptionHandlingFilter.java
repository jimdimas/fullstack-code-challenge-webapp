package com.app.backend.filter;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class ExceptionHandlingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        try{
            filterChain.doFilter(request,response);
        } catch (TokenExpiredException | JWTDecodeException |UsernameNotFoundException e){
            prepareResponseBody(
                    response,
                    HttpStatus.FORBIDDEN.value(),
                    "You need to authenticate to access this resource");
            Logger logger = Logger.getAnonymousLogger();
            logger.log(Level.SEVERE,"An exception was thrown.",e);
        } catch (Exception e){
            prepareResponseBody(
                    response,
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Something went wrong , try again");
            Logger logger = Logger.getAnonymousLogger();
            logger.log(Level.SEVERE,"An exception was thrown.",e);
        }
    }

    private void prepareResponseBody(
            HttpServletResponse response,
            int statusCode,
            String message) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        HashMap<String,String> responseBody = new HashMap<>();
        responseBody.put("message",message);
        response.getWriter().write(new ObjectMapper().writeValueAsString(responseBody));
        response.setStatus(statusCode);
    }
}
