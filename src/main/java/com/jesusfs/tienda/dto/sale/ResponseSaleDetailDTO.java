package com.jesusfs.tienda.dto.sale;

import com.jesusfs.tienda.model.SaleDetail;

public record ResponseSaleDetailDTO(
        String name,
        double price,
        double discount,
        int quantity,
        double total
) {
    public ResponseSaleDetailDTO(SaleDetail saleDetail) {
        this(saleDetail.getProduct().getName(), saleDetail.getUnitPrice(), saleDetail.getDiscount(), saleDetail.getQuantity(), saleDetail.getTotal());
    }
}
