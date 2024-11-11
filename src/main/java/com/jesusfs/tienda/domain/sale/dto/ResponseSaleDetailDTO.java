package com.jesusfs.tienda.domain.sale.dto;

import com.jesusfs.tienda.domain.sale.SaleDetail;

public record ResponseSaleDetailDTO(
        String productName,
        double price,
        double discount,
        int quantity,
        double total
) {
    public ResponseSaleDetailDTO(SaleDetail saleDetail) {
        this(saleDetail.getProduct().getName(), saleDetail.getUnitPrice(), saleDetail.getDiscount(), saleDetail.getQuantity(), saleDetail.getTotal());
    }
}
