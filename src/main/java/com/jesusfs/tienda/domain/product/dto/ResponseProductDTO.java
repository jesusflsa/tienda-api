package com.jesusfs.tienda.domain.product.dto;

import com.jesusfs.tienda.domain.product.Product;

public record ResponseProductDTO(
        Long id,
        String name,
        double price,
        String description,
        String brand
) {
    public ResponseProductDTO(Product product) {
        this(product.getId(), product.getName(), product.getPrice(), product.getDescription(), product.getBrand() != null ? product.getBrand().getName() : null);
    }
}
