package com.jesusfs.tienda.domain.admin.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateAdminDTO(
        @NotBlank
        String username,

        @NotBlank
        String password
) {
}
