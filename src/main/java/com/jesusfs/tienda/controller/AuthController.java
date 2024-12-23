package com.jesusfs.tienda.controller;

import com.jesusfs.tienda.domain.user.User;
import com.jesusfs.tienda.domain.admin.dto.LoginAdminDTO;
import com.jesusfs.tienda.services.JwtService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@PreAuthorize("permitAll()")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginAdminDTO userDTO) {
        Authentication auth = new UsernamePasswordAuthenticationToken(userDTO.username(), userDTO.password());
        Authentication authUser = authenticationManager.authenticate(auth);
        User user = (User) authUser.getPrincipal();
        return jwtService.createToken(user);
    }

}
