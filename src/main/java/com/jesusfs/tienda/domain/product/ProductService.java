package com.jesusfs.tienda.domain.product;

import com.jesusfs.tienda.domain.product.dto.CreateProductDTO;
import com.jesusfs.tienda.domain.product.dto.UpdateProductDTO;
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

    public Product createProduct(@Valid CreateProductDTO productDTO) {
        Product product = new Product(productDTO);
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
        Product product = getProductById(id);
        product.update(productDTO);
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
}
