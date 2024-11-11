package com.jesusfs.tienda.domain.category;

import com.jesusfs.tienda.domain.category.dto.CreateCategoryDTO;
import com.jesusfs.tienda.domain.category.dto.UpdateCategoryDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@Data
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

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        return ((Category) o).getId().equals(this.id);
    }
}
