package com.jesusfs.tienda.domain.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    @Query("SELECT a FROM Admin a JOIN User u ON u.id = a.id WHERE u.active = true")
    List<Admin> findByActiveTrue();

    @Query("SELECT a FROM Admin a JOIN User u ON u.id = a.id WHERE a.id = :id AND u.active = true")
    Optional<Admin> findByIdAndActiveTrue(Long id);

    @Query("SELECT a.id FROM Admin a JOIN User u ON u.id = a.id WHERE u.username = :username")
    boolean existsByUsernameIgnoreCase(String username);
    @Query("SELECT a.id FROM Admin a JOIN User u ON u.id = a.id WHERE u.username = :username AND a.id != :id")
    boolean existsByUsernameIgnoreCaseAndIdNot(String username, Long id);
    @Query("SELECT a FROM Admin a JOIN User u ON u.id = a.id WHERE u.username = :username AND u.active = true")
    Optional<Admin> findByUsernameIgnoreCaseAndActiveTrue(String username);
}
