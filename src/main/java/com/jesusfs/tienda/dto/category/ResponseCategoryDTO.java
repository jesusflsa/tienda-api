package com.jesusfs.tienda.dto.category;

import com.jesusfs.tienda.model.Category;

public record ResponseCategoryDTO(
        Long id,
        String description
) {
    public ResponseCategoryDTO(Category category) {
        this(category.getId(), category.getDescription());
    }
}
