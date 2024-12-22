package com.jesusfs.tienda.domain.product.dto;

public record DetailProductDTO(
        String name,
        String description,
        String brand,
        Double price,
        int reviews,
        String image
) {
}
