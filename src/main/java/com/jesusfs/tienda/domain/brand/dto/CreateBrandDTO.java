package com.jesusfs.tienda.domain.brand.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateBrandDTO(
        @NotBlank
        String name
) { }
