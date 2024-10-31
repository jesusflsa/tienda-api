package com.jesusfs.tienda.model.category;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private boolean active;

    public Category(@Valid CreateCategoryDTO categoryDTO) {
        this.description = categoryDTO.description();
        this.active = true;
    }

    public void update(@Valid UpdateCategoryDTO categoryDTO) {
        if (categoryDTO.description() != null && !this.description.equals(categoryDTO.description())) {
            this.description = categoryDTO.description();
        }
    }
}
