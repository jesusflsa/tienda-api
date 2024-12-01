package com.jesusfs.tienda.controller;

import com.jesusfs.tienda.domain.product.dto.CreateProductDTO;
import com.jesusfs.tienda.domain.product.Product;
import com.jesusfs.tienda.domain.product.dto.ResponseProductDTO;
import com.jesusfs.tienda.domain.product.dto.UpdateProductDTO;
import com.jesusfs.tienda.domain.product.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
@PreAuthorize("isAuthenticated()")
public class ProductController {
    ProductService productService;

    @GetMapping
    @PreAuthorize("permitAll()")
    public List<ResponseProductDTO> getProducts() {
        List<Product> products = productService.getProducts();
        return products.stream().map(ResponseProductDTO::new).toList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseProductDTO getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return new ResponseProductDTO(product);
    }

    @PostMapping
    public ResponseProductDTO createProduct(@RequestBody @Valid CreateProductDTO productDTO) {
        Product product = productService.createProduct(productDTO);
        return new ResponseProductDTO(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseProductDTO updateProduct(@PathVariable Long id, @RequestBody @Valid UpdateProductDTO productDTO) {
        Product product = productService.updateProduct(id, productDTO);
        return new ResponseProductDTO(product);
    }

}
