package com.jesusfs.tienda.domain.brand;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Optional<Brand> findByIdAndActiveTrue(Long id);

    Page<Brand> findByActiveTrue(Pageable page);
}
