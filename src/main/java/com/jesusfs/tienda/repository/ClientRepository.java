package com.jesusfs.tienda.repository;

import com.jesusfs.tienda.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByActiveTrue();

    Optional<Client> findByIdAndActiveTrue(Long id);

    Optional<Client> findByDni(String dni);
    Optional<Client> findByPhone(String phone);

    // Validations
    Optional<Client> findByDniAndIdNot(String dni, Long id);
    Optional<Client> findByPhoneAndIdNot(String phone, Long id);

}
