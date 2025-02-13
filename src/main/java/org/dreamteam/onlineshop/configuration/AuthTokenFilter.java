package org.dreamteam.onlineshop.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.dreamteam.onlineshop.service.UserServiceBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserServiceBean userServiceBean;

    public AuthTokenFilter(JwtUtils jwtUtils, UserServiceBean userServiceBean) {
        this.jwtUtils = jwtUtils;
        this.userServiceBean = userServiceBean;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            if (jwtUtils.validateJwtToken(token)) {
                String username = jwtUtils.getUsernameFromJwtToken(token);
                UserDetails userDetails = userServiceBean.loadUserByUsername(username);
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
            }
        }
        chain.doFilter(request, response);
    }
}
