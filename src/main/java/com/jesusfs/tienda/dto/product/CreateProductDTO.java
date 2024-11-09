package com.jesusfs.tienda.dto.product;

import jakarta.validation.constraints.*;

public record CreateProductDTO(
        @NotBlank
        String name,

        @PositiveOrZero
        Integer stock,

        @Positive
        Double price,

        @DecimalMin(value = "0", message = "Discount must be greater than 0%")
        @DecimalMax(value = "100", message = "Discount must be less than 100%")
        Double discount,

        String description,

        @DecimalMin(value = "0", message = "IVA must be greater than 0%")
        @DecimalMax(value = "100", message = "IVA must be less than 100%")
        Double iva,

        @NotNull
        Long categoryId
) {
}
