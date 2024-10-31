package com.jesusfs.tienda.model.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCategoryDTO(
        @NotNull
        @NotBlank
        String description
) {
}
