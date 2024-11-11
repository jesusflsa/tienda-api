package com.jesusfs.tienda.domain.category.dto;

import com.jesusfs.tienda.domain.category.Category;

public record ResponseCategoryDTO(
        Long id,
        String description
) {
    public ResponseCategoryDTO(Category category) {
        this(category.getId(), category.getDescription());
    }
}
