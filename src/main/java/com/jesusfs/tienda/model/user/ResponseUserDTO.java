package com.jesusfs.tienda.model.user;

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
