package com.jesusfs.tienda.model.product;

import com.jesusfs.tienda.model.category.ResponseCategoryDTO;

public record ResponseProductDTO(
        Long id,
        String name,
        int stock,
        double price,
        String description,
        double iva,
        ResponseCategoryDTO category
) {
    public ResponseProductDTO(Product product) {
        this(product.getId(), product.getName(), product.getStock(), product.getPrice(), product.getDescription(), product.getIva(), new ResponseCategoryDTO(product.getCategory()));
    }
}
