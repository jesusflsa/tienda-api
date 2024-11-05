package com.jesusfs.tienda.dto.sale;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateSaleDetailDTO(
        @NotNull
        @Positive
        @JsonAlias("id")
        Long productId,

        @NotNull
        @Positive
        int quantity
) {
}
