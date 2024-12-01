package com.jesusfs.tienda.domain.product.dto;

import jakarta.validation.constraints.*;

public record CreateProductDTO(
        @NotBlank
        String name,

        @Positive
        Double price,

        String description
) {
}
