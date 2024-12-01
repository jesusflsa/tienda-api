package com.jesusfs.tienda.domain.product.dto;

import jakarta.validation.constraints.*;

public record UpdateProductDTO(
        String name,

        @Positive
        Double price,

        String description
) {
}
