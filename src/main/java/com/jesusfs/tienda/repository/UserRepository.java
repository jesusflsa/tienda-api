package com.jesusfs.tienda.repository;

import com.jesusfs.tienda.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByActiveTrue();

    Optional<User> findByUsernameIgnoreCase(String username);

    Optional<User> findByPhone(String phone);

    Optional<User> findByIdAndActiveTrue(Long id);
}
