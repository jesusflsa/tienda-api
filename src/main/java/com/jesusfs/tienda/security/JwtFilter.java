package com.jesusfs.tienda.security;

import com.jesusfs.tienda.domain.user.UserRepository;
import com.jesusfs.tienda.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    private JwtService jwtService;
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Jwt Filter started.");
        String auth = request.getHeader("Authorization");
        if (auth == null || auth.isBlank() || !auth.startsWith("Bearer ")) {
            log.debug("No token provided.");
            filterChain.doFilter(request, response);
            return;
        }

        String token = auth.substring(7);
        String subject = jwtService.verifyToken(token);
        // TODO: Create filter logic.

    }
}
