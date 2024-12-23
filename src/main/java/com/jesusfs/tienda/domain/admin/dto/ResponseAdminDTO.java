package com.jesusfs.tienda.domain.admin.dto;

import com.jesusfs.tienda.domain.admin.Admin;

public record ResponseAdminDTO(
        Long id,
        String username
) {
    public ResponseAdminDTO(Admin admin) {
        this(admin.getId(), admin.getUsername());
    }
}
