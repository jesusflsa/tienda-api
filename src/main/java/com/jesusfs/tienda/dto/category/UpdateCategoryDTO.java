package com.jesusfs.tienda.dto.category;

import jakarta.validation.constraints.NotBlank;

public record UpdateCategoryDTO(
        @NotBlank(message = "Description cannot be empty.")
        String description
) {
}
