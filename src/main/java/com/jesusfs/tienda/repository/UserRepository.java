package com.jesusfs.tienda.repository;

import com.jesusfs.tienda.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByActiveTrue();

    Optional<User> findByIdAndActiveTrue(Long id);

    Optional<User> findByUsernameIgnoreCase(String username);
    Optional<User> findByUsernameIgnoreCaseAndIdNot(String username, Long id);
    Optional<User> findByUsernameIgnoreCaseAndActiveTrue(String username);

    Optional<User> findByPhone(String phone);
    Optional<User> findByPhoneAndIdNot(String phone, Long id);
}
