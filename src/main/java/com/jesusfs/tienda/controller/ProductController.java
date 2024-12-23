package com.jesusfs.tienda.controller;

import com.jesusfs.tienda.domain.product.dto.CreateProductDTO;
import com.jesusfs.tienda.domain.product.Product;
import com.jesusfs.tienda.domain.product.dto.ResponseProductDTO;
import com.jesusfs.tienda.domain.product.dto.UpdateProductDTO;
import com.jesusfs.tienda.domain.product.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
@PreAuthorize("isAuthenticated()")
public class ProductController {
    ProductService productService;

    @GetMapping
    @PreAuthorize("permitAll()")
    public Page<ResponseProductDTO> getProducts(
            @PageableDefault(size = 25, direction = Sort.Direction.DESC) Pageable page,
            @RequestParam(name = "brand", required = false) String brand,
            @RequestParam(name = "query", required = false) String query,
            @RequestParam(name = "category", required = false) String category
    ) {
        Page<Product> products = productService.getProducts(page, brand, query, category);
        return products.map(ResponseProductDTO::new);
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseProductDTO getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return new ResponseProductDTO(product);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseProductDTO createProduct(@RequestBody @Valid CreateProductDTO productDTO) {
        Product product = productService.createProduct(productDTO);
        return new ResponseProductDTO(product);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseProductDTO updateProduct(@PathVariable Long id, @RequestBody @Valid UpdateProductDTO productDTO) {
        Product product = productService.updateProduct(id, productDTO);
        return new ResponseProductDTO(product);
    }

}
