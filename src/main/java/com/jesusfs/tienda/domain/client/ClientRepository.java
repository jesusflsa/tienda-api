package com.jesusfs.tienda.domain.client;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByEmailIgnoreCase(String email);
}
