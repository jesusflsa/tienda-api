package com.jesusfs.tienda.dto.user;

import com.jesusfs.tienda.model.User;

import java.util.List;

public record ResponseUserDTO(
        Long id,
        String username,
        String fullName,
        String phone,
        List<String> roles
) {
    public ResponseUserDTO(User user) {
        this(user.getId(), user.getUsername(), user.getFirstName() + " " + user.getLastName(), user.getPhone(), user.getRoles().stream().map(role -> role.getName().toString()).toList());
    }
}
