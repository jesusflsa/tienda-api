package com.jesusfs.tienda.domain.client.dto;

import com.jesusfs.tienda.domain.client.Client;

public record ResponseClientDTO(
        Long id,

        String firstName,

        String lastName,

        String dni,

        String phone,

        String streetAddress
) {
    public ResponseClientDTO(Client client) {
        this(client.getId(), client.getFirstName(), client.getLastName(), client.getDni(), client.getPhone(), client.getStreetAddress());
    }
}
