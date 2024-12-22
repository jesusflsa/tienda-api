package com.jesusfs.tienda.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByActiveTrue();

    Optional<User> findByIdAndActiveTrue(Long id);

    boolean existsByUsernameIgnoreCase(String username);
    boolean existsByUsernameIgnoreCaseAndIdNot(String username, Long id);
    Optional<User> findByUsernameIgnoreCaseAndActiveTrue(String username);
}
