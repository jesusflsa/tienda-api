package com.jesusfs.tienda.service;

import com.jesusfs.tienda.model.category.Category;
import com.jesusfs.tienda.model.product.CreateProductDTO;
import com.jesusfs.tienda.model.product.Product;
import com.jesusfs.tienda.model.product.UpdateProductDTO;
import com.jesusfs.tienda.repository.CategoryRepository;
import com.jesusfs.tienda.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public Product createProduct(@Valid CreateProductDTO productDTO) {
        // Validations
        // Category not exists
        Optional<Category> opCategory = categoryRepository.findByIdAndActiveTrue(productDTO.categoryId());
        if (opCategory.isEmpty())
            throw new RuntimeException("This category not exists.");

        Category category = opCategory.get();

        Product product = new Product(productDTO, category);
        return productRepository.save(product);
    }

    public List<Product> getProducts() {
        return productRepository.findByActiveTrue();
    }

    public Boolean deleteProduct(Long id) {
        // Validations
        // Product not exists or is already not active
        Optional<Product> opProduct = productRepository.findByIdAndActiveTrue(id);
        if (opProduct.isEmpty())
            return false;

        // Deleting product
        Product product = opProduct.get();
        product.setActive(false);
        productRepository.save(product);
        return true;
    }

    public Product updateProduct(@PathVariable Long id, @RequestBody @Valid UpdateProductDTO productDTO) {
        // Validations
        // Product exists
        Optional<Product> opProduct = productRepository.findByIdAndActiveTrue(id);
        if (opProduct.isEmpty())
            throw new RuntimeException("Product not exists.");

        // Category not exists
        Category category = null;

        if (productDTO.categoryId() != null) {
            Optional<Category> opCategory = categoryRepository.findByIdAndActiveTrue(productDTO.categoryId());
            if (opCategory.isEmpty())
                throw new RuntimeException("Category not exists.");

            category = opCategory.get();
        }

        // Updating Product
        Product product = opProduct.get();
        product.update(productDTO, category);
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        Optional<Product> opProduct = productRepository.findByIdAndActiveTrue(id);
        // Validations
        // Product not exists
        if (opProduct.isEmpty())
            throw new RuntimeException("Product not exists");

        // Get product by id
        return opProduct.get();
    }
}
