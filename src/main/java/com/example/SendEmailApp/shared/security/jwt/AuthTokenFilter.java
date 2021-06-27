package com.example.SendEmailApp.shared.security.jwt;

import com.example.SendEmailApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthTokenFilter extends OncePerRequestFilter {

    private JwtUtils jwtUtils;
    private UserService userService;

    @Autowired
    public AuthTokenFilter(JwtUtils jwtUtils,UserService userService){
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    public AuthTokenFilter() {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        try {
            String jwt = parseJwt(httpServletRequest);
            if (jwt != null && jwtUtils.validateToken(jwt)){
                String login = jwtUtils.getUsernameFromToken(jwt);
                UserDetails userDetails =  userService.loadUserByUsername(login);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }catch (Exception e){
            System.err.println(e);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
    private String parseJwt(HttpServletRequest httpServletRequest){
        String header = httpServletRequest.getHeader("Authorization");
        if (StringUtils.hasText(header) && header.startsWith("Bearer ")){
            return header.substring(7 , header.length());
        }
        return null;
    }
}
