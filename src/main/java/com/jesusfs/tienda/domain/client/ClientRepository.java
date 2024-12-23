package com.jesusfs.tienda.domain.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT EXISTS (SELECT c.id FROM Client c JOIN User u ON u.id = c.id WHERE u.username = :email)")
    boolean existsByEmailIgnoreCase(String email);
}
