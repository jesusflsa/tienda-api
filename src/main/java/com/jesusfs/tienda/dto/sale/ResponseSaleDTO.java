package com.jesusfs.tienda.dto.sale;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jesusfs.tienda.model.Sale;

import java.util.List;

public record ResponseSaleDTO(
        Long id,

        @JsonProperty("client_id")
        Long clientId,

        List<ResponseSaleDetailDTO> products,

        @JsonProperty("sale_date")
        String saleDate,

        double total,

        double discount
) {
    public ResponseSaleDTO(Sale sale) {
        this(sale.getId(), sale.getClient().getId(), sale.getSaleDetails().stream().map(ResponseSaleDetailDTO::new).toList(), sale.getSaleDate().toString(), sale.getTotal(), sale.getSubtotal() * sale.getDiscount() / 100);
    }
}
