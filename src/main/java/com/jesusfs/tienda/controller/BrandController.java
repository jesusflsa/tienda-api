package com.jesusfs.tienda.controller;

import com.jesusfs.tienda.domain.brand.Brand;
import com.jesusfs.tienda.domain.brand.BrandService;
import com.jesusfs.tienda.domain.brand.dto.CreateBrandDTO;
import com.jesusfs.tienda.domain.brand.dto.ResponseBrandDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/brands")
@AllArgsConstructor
@Slf4j
public class BrandController {
    private BrandService brandService;

    @PostMapping
    public ResponseBrandDTO createBrand(@RequestBody @Valid CreateBrandDTO brandDTO) {
        log.info("BrandController::createBrand execution started.");
        Brand brand = brandService.createBrand(brandDTO);
        return new ResponseBrandDTO(brand);
    }

    @GetMapping
    public List<ResponseBrandDTO> getBrands(
            @PageableDefault(size = 5) Pageable page,
            @RequestParam(name = "query", required = false) String query
            ) {
        List<Brand> brands = brandService.getBrands(page, query);
        return brands.stream().map(ResponseBrandDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseBrandDTO getBrandById(@PathVariable Long id) {
        Brand brand = brandService.getBrandById(id);
        return new ResponseBrandDTO(brand);
    }
}
