package com.jesusfs.tienda.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCategoryDTO(
        @NotNull
        @NotBlank
        String description
) {
}
