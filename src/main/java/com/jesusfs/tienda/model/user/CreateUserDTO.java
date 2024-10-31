package com.jesusfs.tienda.model.user;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserDTO(
        @NotNull
        @NotBlank
        String username,

        @NotNull
        @NotBlank
        String password,

        @NotNull
        @NotBlank
        @JsonAlias("first_name")
        String firstName,

        @NotNull
        @NotBlank
        @JsonAlias("last_name")
        String lastName,

        @NotBlank
        String phone
) {
}
