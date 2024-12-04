package com.jesusfs.tienda.domain.brand.dto;

import com.jesusfs.tienda.domain.brand.Brand;

public record ResponseBrandDTO(
        Long id,
        String name
) {
    public ResponseBrandDTO(Brand brand) {
        this(brand.getId(), brand.getName());
    }
}
