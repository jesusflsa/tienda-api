package com.jesusfs.tienda.controller;

import com.jesusfs.tienda.domain.admin.Admin;
import com.jesusfs.tienda.domain.admin.AdminServiceImpl;
import com.jesusfs.tienda.domain.admin.dto.CreateAdminDTO;
import com.jesusfs.tienda.services.JwtService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/admins")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private AdminServiceImpl adminService;
    private JwtService jwtService;

    @PostMapping
    public String createAdmin(@Valid @RequestBody CreateAdminDTO adminDTO) {
        Admin admin = adminService.createAdmin(adminDTO);
        return jwtService.createToken(admin);
    }

}
