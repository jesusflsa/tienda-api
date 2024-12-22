package com.jesusfs.tienda.domain.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByIdAndActiveTrue(Long id);
    Page<Category> findByActiveTrue(Pageable page);

    @Query("SELECT DISTINCT c FROM Category c JOIN c.products p WHERE p.name LIKE %:query%")
    List<Category> getCategoriesFromQuery(String query);

    @Query("SELECT c FROM Category c WHERE c.id in (:ids) AND c.active = true")
    List<Category> findAllByIdAndActiveTrue(List<Long> ids);
}
