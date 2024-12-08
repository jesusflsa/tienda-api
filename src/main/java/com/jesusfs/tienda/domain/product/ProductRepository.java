package com.jesusfs.tienda.domain.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT p FROM Product p WHERE p.brand.id in (:brands) AND p.active = true")
    Page<Product> searchProductsByBrands(@Param("brands") List<Long> brands, Pageable page);
    @Query(value = "SELECT p FROM Product p WHERE p.name LIKE %:query% AND p.active = true")
    Page<Product> searchProductsByQuery(@Param("query") String query, Pageable page);
    @Query(value = "SELECT p FROM Product p WHERE p.brand.id in (:brands) AND p.name LIKE %:query% AND p.active = true")
    Page<Product> searchProductsByQueryAndBrands(@Param("query") String query, @Param("brands") List<Long> brands, Pageable page);

    Optional<Product> findByIdAndActiveTrue(Long id);
    Page<Product> findByActiveTrue(Pageable page);
}
