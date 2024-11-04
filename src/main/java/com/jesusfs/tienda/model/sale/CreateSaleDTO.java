package com.jesusfs.tienda.model.sale;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public record CreateSaleDTO(
        @NotNull
        @Positive
        @JsonAlias("client_id")
        Long clientId,

        @NotNull
        List<CreateSaleDetailDTO> products,

        @NotNull
        @PositiveOrZero
        double discount
) {
}
