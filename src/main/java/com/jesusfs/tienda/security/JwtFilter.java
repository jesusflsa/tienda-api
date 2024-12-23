package com.jesusfs.tienda.security;

import com.jesusfs.tienda.domain.user.User;
import com.jesusfs.tienda.domain.user.UserRepository;
import com.jesusfs.tienda.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("JwtFilter::doFilterInternal started execution.");
        String auth = request.getHeader("Authorization");
        if (auth == null || auth.isBlank() || !auth.startsWith("Bearer ")) {
            log.debug("No token provided.");
            filterChain.doFilter(request, response);
            return;
        }

        String token = auth.substring(7);
        String subject = jwtService.verifyToken(token);
        // TODO: Create filter logic.
        Optional<User> opUser = userRepository.findByUsernameIgnoreCaseAndActiveTrue(subject);
        if (opUser.isEmpty()) {
            log.info("This user not exists.");
            filterChain.doFilter(request, response);
            return;
        }
        User user = opUser.get();
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
