package com.jesusfs.tienda.model.product;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record UpdateProductDTO(
        @NotBlank
        String name,

        @Positive
        Integer stock,

        @Positive
        Double price,

        @PositiveOrZero
        Double discount,

        String description,

        @Positive
        Double iva,

        @JsonAlias("category_id")
        Long categoryId
) {
}
