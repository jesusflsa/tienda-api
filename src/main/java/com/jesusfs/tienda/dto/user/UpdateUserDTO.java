package com.jesusfs.tienda.dto.user;

import com.fasterxml.jackson.annotation.JsonAlias;

public record UpdateUserDTO(
        String username,

        String password,

        @JsonAlias("first_name")
        String firstName,

        @JsonAlias("last_name")
        String lastName,

        String phone
) {
}
