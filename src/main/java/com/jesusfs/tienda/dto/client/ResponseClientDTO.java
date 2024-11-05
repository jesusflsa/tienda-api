package com.jesusfs.tienda.dto.client;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.jesusfs.tienda.model.Client;

public record ResponseClientDTO(
        Long id,

        @JsonAlias("first_name")
        String firstName,

        @JsonAlias("last_name")
        String lastName,

        String dni,

        String phone,

        String streetAddress
) {
    public ResponseClientDTO(Client client) {
        this(client.getId(), client.getFirstName(), client.getLastName(), client.getDni(), client.getPhone(), client.getStreetAddress());
    }
}
