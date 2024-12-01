package com.jesusfs.tienda.domain.product;


import com.jesusfs.tienda.domain.product.dto.CreateProductDTO;
import com.jesusfs.tienda.domain.product.dto.UpdateProductDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "price")
    private double price;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private boolean active = true;

    public Product(@Valid CreateProductDTO productDTO) {
        this.name = productDTO.name();
        this.price = productDTO.price();
        this.description = productDTO.description();
    }

    public void update(@Valid UpdateProductDTO productDTO) {
        this.name = productDTO.name();
        this.price = productDTO.price();
        this.description = productDTO.description();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        return ((Product) o).getId().equals(this.id);
    }
}
