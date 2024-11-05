package com.jesusfs.tienda.repository;

import com.jesusfs.tienda.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    Optional<Sale> findByIdAndActiveTrue(Long id);
    List<Sale> findByActiveTrue();
}
