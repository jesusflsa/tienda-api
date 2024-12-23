package com.jesusfs.tienda.domain.user.dto;

import com.jesusfs.tienda.domain.user.User;

public record ResponseUserDTO(
        Long id,
        String username
) {
    public ResponseUserDTO(User user) {
        this(user.getId(), user.getUsername());
    }
}
