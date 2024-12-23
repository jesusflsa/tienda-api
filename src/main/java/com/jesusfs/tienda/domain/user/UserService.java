package com.jesusfs.tienda.domain.user;


import java.util.List;

public interface UserService {
    List<User> getUsers();
    User getUserById(Long id);
    void deleteUser(Long id);
}
