package com.jesusfs.tienda.dto.product;

import com.jesusfs.tienda.model.Product;

public record ResponseProductDTO(
        Long id,
        String name,
        int stock,
        double price,
        double discount,
        String description,
        double iva,
        Long categoryId
) {
    public ResponseProductDTO(Product product) {
        this(product.getId(), product.getName(), product.getStock(), product.getPrice(), product.getDiscount(), product.getDescription(), product.getIva(), product.getCategory().getId());
    }
}
