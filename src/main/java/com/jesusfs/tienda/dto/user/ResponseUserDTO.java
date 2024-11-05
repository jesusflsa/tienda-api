package com.jesusfs.tienda.dto.user;

import com.jesusfs.tienda.model.User;

public record ResponseUserDTO(
        Long id,
        String username,
        String fullName,
        String phone
) {
    public ResponseUserDTO(User user) {
        this(user.getId(), user.getUsername(), user.getFirstName() + " " + user.getLastName(), user.getPhone());
    }
}
