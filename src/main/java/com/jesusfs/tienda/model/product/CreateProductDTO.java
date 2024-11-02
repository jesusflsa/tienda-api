package com.jesusfs.tienda.model.product;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateProductDTO(
        @NotNull
        @NotBlank
        String name,

        Integer stock,

        Double price,

        Double discount,

        String description,

        Double iva,

        @JsonAlias("category_id")
        Long categoryId
) {
}
