package com.jesusfs.tienda.domain.brand;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Optional<Brand> findByIdAndActiveTrue(Long id);

    Page<Brand> findByActiveTrue(Pageable page);

    @Query("SELECT b FROM Brand b JOIN Product p ON p.brand.id = b.id WHERE p.name LIKE %:query% GROUP BY b.id")
    List<Brand> searchByProductQuery(String query);
}
