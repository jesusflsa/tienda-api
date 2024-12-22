package com.jesusfs.tienda.domain.brand;

import com.jesusfs.tienda.domain.brand.dto.CreateBrandDTO;
import com.jesusfs.tienda.domain.brand.dto.UpdateBrandDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Brand> getBrands(Pageable page, String query) {
        if (query == null || query.isBlank() || query.length() < 3) return getBrands(page).getContent();

        return getBrandsByQuery(query);
    }

    public Page<Brand> getBrands(Pageable page) {
        return brandRepository.findByActiveTrue(page);
    }

    public List<Brand> getBrandsByQuery(String query) {
        return brandRepository.searchByProductQuery(query);
    }

    public Brand updateBrand(Long id, UpdateBrandDTO brandDTO) {
        Brand brand = getBrandById(id);
        brand.setName(brandDTO.name());
        return brandRepository.save(brand);
    }

    public boolean deleteBrand(Long id) {
        Optional<Brand> opBrand = brandRepository.findByIdAndActiveTrue(id);
        if (opBrand.isEmpty()) return false;

        Brand brand = opBrand.get();
        brand.setActive(false);
        brandRepository.save(brand);
        return true;
    }
}
