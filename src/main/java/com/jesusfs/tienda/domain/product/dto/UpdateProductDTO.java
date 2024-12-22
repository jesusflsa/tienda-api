package com.jesusfs.tienda.domain.product.dto;

import jakarta.validation.constraints.*;

import java.util.List;

public record UpdateProductDTO(
        String name,

        @Positive
        Double price,

        String description,

        Long brand,

        List<Long> categories
) {
}
