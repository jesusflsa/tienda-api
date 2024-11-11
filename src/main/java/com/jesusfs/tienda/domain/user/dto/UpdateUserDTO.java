package com.jesusfs.tienda.domain.user.dto;

import java.util.List;

public record UpdateUserDTO(
        String username,

        String password,

        String firstName,

        String lastName,

        String phone,

        List<Long> roles
) {
}
