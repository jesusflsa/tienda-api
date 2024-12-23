package com.jesusfs.tienda.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserDTO(
        @NotBlank
        @Email
        String username,

        @NotBlank
        String password
) {
}
