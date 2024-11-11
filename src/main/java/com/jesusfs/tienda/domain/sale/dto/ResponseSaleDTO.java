package com.jesusfs.tienda.domain.sale.dto;

import com.jesusfs.tienda.domain.sale.Sale;

import java.util.List;

public record ResponseSaleDTO(
        Long id,

        Long clientId,

        List<ResponseSaleDetailDTO> products,

        String saleDate,

        double total,

        double discount
) {
    public ResponseSaleDTO(Sale sale) {
        this(sale.getId(), sale.getClient().getId(), sale.getSaleDetails().stream().map(ResponseSaleDetailDTO::new).toList(), sale.getSaleDate().toString(), sale.getTotal(), sale.getSubtotal() * sale.getDiscount() / 100);
    }
}
