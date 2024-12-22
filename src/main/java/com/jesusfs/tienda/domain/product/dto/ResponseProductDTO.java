package com.jesusfs.tienda.domain.product.dto;

import com.jesusfs.tienda.domain.category.Category;
import com.jesusfs.tienda.domain.product.Product;

import java.util.List;

public record ResponseProductDTO(
        Long id,
        String name,
        double price,
        String description,
        String brand,
        List<String> categories
) {
    public ResponseProductDTO(Product product) {
        this(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getBrand() != null ? product.getBrand().getName() : null,
                product.getCategories().stream().map(Category::getName).toList()
        );
    }
}
