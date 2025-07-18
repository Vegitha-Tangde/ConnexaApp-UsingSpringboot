package com.example.Connexa.Connexa.filters;

import com.example.Connexa.Connexa.entities.User;
import com.example.Connexa.Connexa.exceptions.UserException;
import com.example.Connexa.Connexa.services.JwtService;
import com.example.Connexa.Connexa.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");

        if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = requestTokenHeader.split("Bearer ")[1];

        Long userId = jwtService.getIdFromToken(token);
        if(userId != null && SecurityContextHolder.getContext().getAuthentication() == null){
            try {
                User user = userService.findUserById(userId);
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(user,null,null);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } catch (UserException e) {
                throw new RuntimeException(e);
            }
        }

        filterChain.doFilter(request,response);
    }
}
