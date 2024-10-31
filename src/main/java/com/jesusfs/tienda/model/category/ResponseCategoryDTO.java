package com.jesusfs.tienda.model.category;

public record ResponseCategoryDTO(
        Long id,
        String description
) {
    public ResponseCategoryDTO(Category category) {
        this(category.getId(), category.getDescription());
    }
}
