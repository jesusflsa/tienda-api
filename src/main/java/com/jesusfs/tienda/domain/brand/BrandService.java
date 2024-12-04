package com.jesusfs.tienda.domain.brand;

import com.jesusfs.tienda.domain.brand.dto.CreateBrandDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BrandService {
    private BrandRepository brandRepository;

    public Brand createBrand(@Valid CreateBrandDTO brandDTO) {
        Brand brand = new Brand();
        brand.setName(brandDTO.name());
        return brandRepository.save(brand);
    }

    public Brand getBrandById(Long id) {
        Optional<Brand> opBrand = brandRepository.findByIdAndActiveTrue(id);
        if (opBrand.isEmpty()) throw new RuntimeException("This brand not exists.");
        return opBrand.get();
    }

    public Page<Brand> getBrands(Pageable page) {
        return brandRepository.findByActiveTrue(page);
    }
}
