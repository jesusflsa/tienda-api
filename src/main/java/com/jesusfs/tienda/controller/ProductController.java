package com.jesusfs.tienda.controller;

import com.jesusfs.tienda.dto.product.CreateProductDTO;
import com.jesusfs.tienda.model.Product;
import com.jesusfs.tienda.dto.product.ResponseProductDTO;
import com.jesusfs.tienda.dto.product.UpdateProductDTO;
import com.jesusfs.tienda.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    ProductService productService;

    @PostMapping
    public ResponseProductDTO createProduct(@RequestBody @Valid CreateProductDTO productDTO) {
        Product product = productService.createProduct(productDTO);
        return new ResponseProductDTO(product);
    }

    @GetMapping
    public List<ResponseProductDTO> getProducts() {
        List<Product> products = productService.getProducts();
        return products.stream().map(ResponseProductDTO::new).toList();
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

    @GetMapping("/{id}")
    public ResponseProductDTO getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return new ResponseProductDTO(product);
    }
}
