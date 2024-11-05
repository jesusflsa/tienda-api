package com.jesusfs.tienda.service;

import com.jesusfs.tienda.model.Category;
import com.jesusfs.tienda.dto.product.CreateProductDTO;
import com.jesusfs.tienda.model.Product;
import com.jesusfs.tienda.dto.product.UpdateProductDTO;
import com.jesusfs.tienda.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    private CategoryService categoryService;

    public Product createProduct(@Valid CreateProductDTO productDTO) {
        Category category = categoryService.getCategoryById(productDTO.categoryId());
        Product product = new Product(productDTO, category);
        return productRepository.save(product);
    }

    public List<Product> getProducts() {
        return productRepository.findByActiveTrue();
    }

    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        product.setActive(false);
        productRepository.save(product);
    }

    public Product updateProduct(@PathVariable Long id, @RequestBody @Valid UpdateProductDTO productDTO) {
        Category category = categoryService.getCategoryById(productDTO.categoryId());
        Product product = getProductById(id);
        product.update(productDTO, category);
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        // Validations
        // Product not exists
        Optional<Product> opProduct = productRepository.findByIdAndActiveTrue(id);
        if (opProduct.isEmpty()) throw new RuntimeException("Product not exists");

        // Get product by id
        return opProduct.get();
    }

    public void buy(Product product, Integer quantity) {
        if (product.getStock() <= quantity) throw new RuntimeException("Cannot buy this product.");
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);
    }
}
