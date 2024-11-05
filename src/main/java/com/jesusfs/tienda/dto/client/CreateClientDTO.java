package com.jesusfs.tienda.dto.client;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateClientDTO(
        @NotNull
        @NotBlank
        @JsonAlias("first_name")
        String firstName,

        @NotNull
        @NotBlank
        @JsonAlias("last_name")
        String lastName,

        @NotNull
        @NotBlank
        String dni,

        String phone,

        String streetAddress
) { }
