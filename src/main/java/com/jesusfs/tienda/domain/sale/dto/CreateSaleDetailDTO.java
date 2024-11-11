package com.jesusfs.tienda.domain.sale.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateSaleDetailDTO(
        @NotNull(message = "Product ID cannot be empty.")
        Long productId,

        @NotNull(message = "Quantity cannot be empty.")
        @Positive(message = "Quantity must be positive.")
        int quantity
) {
}
