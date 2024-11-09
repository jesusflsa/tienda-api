package com.jesusfs.tienda.dto.sale;

import jakarta.validation.constraints.*;

import java.util.List;

public record CreateSaleDTO(
        @NotNull(message = "Client ID cannot be empty.")
        Long clientId,

        @NotNull(message = "Products cannot be empty.")
        List<CreateSaleDetailDTO> products,

        @DecimalMin(value = "0", message = "Discount must be greater than 0%")
        @DecimalMax(value = "100", message = "Discount must be less than 100%")
        double discount
) {
}
