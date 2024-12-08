package com.jesusfs.tienda.domain.product;

import com.jesusfs.tienda.domain.brand.Brand;
import com.jesusfs.tienda.domain.brand.BrandService;
import com.jesusfs.tienda.domain.product.dto.CreateProductDTO;
import com.jesusfs.tienda.domain.product.dto.UpdateProductDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {
    private ProductRepository productRepository;

    private BrandService brandService;

    public Product createProduct(@Valid CreateProductDTO productDTO) {
        log.info("ProductService::createProduct execution started.");
        Product product = new Product();
        product.setName(productDTO.name());
        product.setPrice(productDTO.price());
        product.setDescription(productDTO.description());
        Brand brand = brandService.getBrandById(productDTO.brand());
        product.setBrand(brand);
        log.debug("ProductService::createProduct request parameters: {}", productDTO);
        log.info("ProductService::createProduct execution ended.");
        return productRepository.save(product);
    }

    public Page<Product> getProducts(Pageable page, String brand, String query) {
        Page<Product> productList = null;
        log.info("ProductService::getProducts execution started.");
        log.debug("ProductService::getProducts Brand ids: {}", brand);
        log.debug("ProductService::getProducts Query: {}", query);

        boolean hasBrands = brand != null && !brand.isBlank();
        boolean hasQuery = query != null && !query.isBlank() && query.trim().length() > 3;

        List<Long> brandIds = hasBrands ? Arrays.stream(brand.split(",")).map(Long::valueOf).toList() : Collections.emptyList();

        if (hasBrands && hasQuery) {
            productList = productRepository.searchProductsByQueryAndBrands(query, brandIds, page);
        } else if (hasBrands) {
            productList = productRepository.searchProductsByBrands(brandIds, page);
        } else if (hasQuery) {
            productList = productRepository.searchProductsByQuery(query, page);
        } else {
            productList = productRepository.findByActiveTrue(page);
        }

        log.info("ProductService::getProducts execution ended.");
        return productList;
    }

    public void deleteProduct(Long id) {
        log.info("ProductService::deleteProduct execution started.");
        Product product = getProductById(id);
        product.setActive(false);
        productRepository.save(product);
        log.info("ProductService::deleteProduct execution ended.");
    }

    public Product updateProduct(@PathVariable Long id, @RequestBody @Valid UpdateProductDTO productDTO) {
        log.info("ProductService::updateProduct execution started.");
        log.debug("ProductService::updateProduct request parameters: {}", productDTO);
        Product product = getProductById(id);
        product.setName(productDTO.name());
        product.setPrice(productDTO.price());
        product.setDescription(productDTO.description());

        Brand brand = brandService.getBrandById(productDTO.brand());
        product.setBrand(brand);

        log.info("ProductService::updateProduct execution ended.");
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        log.info("ProductService::getProductById execution started.");
        Optional<Product> opProduct = productRepository.findByIdAndActiveTrue(id);
        if (opProduct.isEmpty()) {
            log.error("ProductService::getProductById Product not exists.");
            throw new RuntimeException("Product not exists");
        }
        log.info("ProductService::getProductById execution ended.");
        return opProduct.get();
    }
}
