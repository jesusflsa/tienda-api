package com.jesusfs.tienda.domain.client.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreateClientDTO(
        @NotBlank
        String fullName,

        @Email
        @NotBlank
        String email,

        @NotBlank
        @Min(value = 8)
        String password
) {}
