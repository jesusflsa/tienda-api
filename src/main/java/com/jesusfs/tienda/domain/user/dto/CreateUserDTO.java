package com.jesusfs.tienda.domain.user.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateUserDTO(
        @NotBlank
        String username,

        @NotBlank
        String password
) {
}
