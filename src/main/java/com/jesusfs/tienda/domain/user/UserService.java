package com.jesusfs.tienda.domain.user;

import com.jesusfs.tienda.domain.user.dto.CreateUserDTO;
import com.jesusfs.tienda.domain.user.dto.UpdateUserDTO;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User getUserById(Long id);
    User createUser(CreateUserDTO userDTO);
    void deleteUser(Long id);
    User updateUser(Long id, UpdateUserDTO userDTO);
}
