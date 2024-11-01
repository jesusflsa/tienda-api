package com.jesusfs.tienda.model.client;

import com.fasterxml.jackson.annotation.JsonAlias;

public record UpdateClientDTO(
        @JsonAlias("first_name")
        String firstName,

        @JsonAlias("last_name")
        String lastName,

        String dni,

        String phone,

        String streetAddress
) {}
