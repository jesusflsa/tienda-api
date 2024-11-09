package com.jesusfs.tienda.dto.user;

import jakarta.validation.constraints.NotBlank;

public record CreateUserDTO(
        @NotBlank
        String username,

        @NotBlank
        String password,

        @NotBlank
        String firstName,

        @NotBlank
        String lastName,

        @NotBlank
        String phone
) {
}
