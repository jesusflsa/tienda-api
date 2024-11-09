package com.jesusfs.tienda.model;

import com.jesusfs.tienda.dto.product.CreateProductDTO;
import com.jesusfs.tienda.dto.product.UpdateProductDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "stock")
    private int stock;

    @Column(name = "price")
    private double price;

    @Column(name = "discount")
    private double discount;

    @Column(name = "description")
    private String description;

    @Column(name = "iva")
    private double iva;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "active")
    private boolean active;

    public Product(@Valid CreateProductDTO productDTO, Category category) {
        this.name = productDTO.name();
        this.stock = productDTO.stock();
        this.price = productDTO.price();
        this.discount = Optional.ofNullable(productDTO.discount()).orElse(0.0);
        this.description = productDTO.description();
        this.iva = productDTO.iva();
        this.category = category;
        this.active = true;
    }

    public void update(@Valid UpdateProductDTO productDTO, Category category) {
        if (productDTO.name() != null && !this.name.equals(productDTO.name())) {
            this.name = productDTO.name();
        }

        if (productDTO.stock() != null && this.stock != productDTO.stock()) {
            this.stock = productDTO.stock();
        }

        if (productDTO.price() != null && this.price != productDTO.price()) {
            this.price = productDTO.price();
        }

        if (this.description == null || this.description.equals(productDTO.description())) {
            this.description = productDTO.description();
        }

        if (productDTO.iva() != null && this.iva != productDTO.iva()) {
            this.iva = productDTO.iva();
        }

        if (productDTO.discount() != null && this.discount != productDTO.discount()) {
            this.discount = productDTO.discount();
        }

        if (category != null && !this.category.equals(category)) {
            this.category = category;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        return ((Product) o).getId().equals(this.id);
    }
}
