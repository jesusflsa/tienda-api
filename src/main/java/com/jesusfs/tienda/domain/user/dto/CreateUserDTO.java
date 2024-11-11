package com.jesusfs.tienda.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

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
        String phone,

        @NotNull
        List<Long> roles
) {
}
