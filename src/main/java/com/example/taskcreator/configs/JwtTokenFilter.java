package com.example.taskcreator.configs;

import com.example.taskcreator.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;

    @Autowired
    public JwtTokenFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
    ) throws ServletException, IOException {
        // Get token from header
        String BearerToken = request.getHeader("Authorization");
        String token = extractTokenFromBearer(BearerToken);

        boolean isValidToken = validateToken(token);

        if (!isValidToken) {
            HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response);
            responseWrapper.setStatus(HttpStatus.UNAUTHORIZED.value());
            responseWrapper.setContentType("application/json");
            responseWrapper.getWriter().write("{ \"error\": \"Invalid or expired token \" }");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private boolean validateToken(String token) {
        try {
            if(token == null) {
                return false;
            }

            boolean expire = jwtUtil.isTokenExpired(token, false);
            if(expire) {
                return false;
            }
        }catch (Exception e){
            return false;
        }

        return true;
    }

    private String extractTokenFromBearer(String tokenWithBearer) {
        if (tokenWithBearer != null && tokenWithBearer.startsWith("Bearer ")) {
            return tokenWithBearer.substring(7);
        }
        return null;
    }
}
