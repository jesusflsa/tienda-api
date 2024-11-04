package com.jesusfs.tienda.model.sale;

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
