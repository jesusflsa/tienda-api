package com.jesusfs.tienda.domain.client.dto;

import com.jesusfs.tienda.domain.client.Client;

public record ResponseClientDTO(
        String fullName,
        String email
) {
    public ResponseClientDTO(Client client) {
        this(client.getFullName(), client.getUsername());
    }
}
