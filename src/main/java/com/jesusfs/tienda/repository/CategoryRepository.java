package com.jesusfs.tienda.repository;

import com.jesusfs.tienda.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByActiveTrue();
    Optional<Category> findByIdAndActiveTrue(Long id);

    Optional<Category> findByDescriptionIgnoreCase(String description);
    Optional<Category> findByDescriptionIgnoreCaseAndIdNot(String description, Long id);
}
