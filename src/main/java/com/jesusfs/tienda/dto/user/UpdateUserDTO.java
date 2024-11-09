package com.jesusfs.tienda.dto.user;

public record UpdateUserDTO(
        String username,

        String password,

        String firstName,

        String lastName,

        String phone
) {
}
