package com.jesusfs.tienda.model.user;

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
