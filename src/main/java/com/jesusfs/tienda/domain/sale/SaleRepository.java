package com.jesusfs.tienda.domain.sale;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    Optional<Sale> findByIdAndActiveTrue(Long id);
    List<Sale> findByActiveTrue();
}
