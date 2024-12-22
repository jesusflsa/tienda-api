package com.jesusfs.tienda.domain.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT p FROM Product p WHERE p.brand.id in (:brands) AND p.active = true AND p.brand.active = true")
    Page<Product> searchProductsByBrands(@Param("brands") List<Long> brands, Pageable page);

    @Query(value = "SELECT p FROM Product p WHERE p.name LIKE %:query% AND p.active = true")
    Page<Product> searchProductsByQuery(@Param("query") String query, Pageable page);

    @Query(value = "SELECT p FROM Product p WHERE p.brand.id in (:brands) AND p.name LIKE %:query% AND p.active = true AND p.brand.active = true")
    Page<Product> searchProductsByQueryAndBrands(@Param("query") String query, @Param("brands") List<Long> brands, Pageable page);

    @Query(value = "SELECT p FROM Product p JOIN p.categories c WHERE c.id in (:categories) AND p.brand.id in (:brands) AND p.name LIKE %:query% AND p.active = TRUE AND p.brand.active = true AND c.active = true")
    Page<Product> searchProductsByQueryAndBrandsAndCategories(@Param("query") String query, @Param("brands") List<Long> brands, @Param("categories") List<Long> categories, Pageable page);

    @Query(value = "SELECT p FROM Product p JOIN p.categories c WHERE p.name LIKE %:query% AND c.id in (:categories) AND p.active = TRUE AND c.active = true")
    Page<Product> searchProductsByQueryAndCategories(@Param("query") String query,@Param("categories") List<Long> categories, Pageable page);

    @Query(value = "SELECT p FROM Product p JOIN p.categories c WHERE p.brand.id in (:brands) AND c.id in (:categories) AND p.active = TRUE AND p.brand.active = TRUE AND c.active = TRUE")
    Page<Product> searchProductsByBrandsAndCategories(@Param("brands") List<Long> brands, @Param("categories") List<Long> categories, Pageable page);

    @Query(value = "SELECT p FROM Product p JOIN p.categories c WHERE c.id in (:categories) AND p.active = TRUE AND c.active = TRUE")
    Page<Product> searchProductsByCategories(@Param("categories") List<Long> categories, Pageable page);

    Optional<Product> findByIdAndActiveTrue(Long id);

    Page<Product> findByActiveTrue(Pageable page);

    @Query(value = "SELECT p FROM Product p WHERE p.brand.id = :brandId")
    Page<Product> findByBrand(@Param("brandId") Long brandId, Pageable page);

    @Query(value = "SELECT p FROM Product p JOIN Category c ON c.id = :categoryId")
    Page<Product> findByCategory(@Param("categoryId") Long categoryId, Pageable page);
}
