package com.jesusfs.tienda.domain.category.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryDTO(
        @NotBlank(message = "Description cannot be empty.")
        String description
) {
}
