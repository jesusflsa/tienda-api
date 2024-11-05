package com.jesusfs.tienda.service;

import com.jesusfs.tienda.model.Category;
import com.jesusfs.tienda.dto.category.CreateCategoryDTO;
import com.jesusfs.tienda.dto.category.UpdateCategoryDTO;
import com.jesusfs.tienda.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Category createCategory(@Valid CreateCategoryDTO categoryDTO) {
        // Validations
        // Category's name exists
        Optional<Category> opCategory = categoryRepository.findByDescriptionIgnoreCase(categoryDTO.description());
        if (opCategory.isPresent())
            throw new RuntimeException("The category's description already exists.");

        // Create Category
        Category category = new Category(categoryDTO);
        return categoryRepository.save(category);
    }

    public List<Category> getCategories() {
        return categoryRepository.findByActiveTrue();
    }

    public Boolean deleteCategory(Long id) {
        // Validations
        // Category not exists or is already not active
        Optional<Category> opCategory = categoryRepository.findById(id);
        if (opCategory.isEmpty() || !opCategory.get().isActive())
            return false;

        // Deleting category
        Category category = opCategory.get();
        category.setActive(false);
        categoryRepository.save(category);
        return true;
    }

    public Category updateCategory(Long id, @Valid UpdateCategoryDTO categoryDTO) {
        // Validations
        // Category's description already exists
        Optional<Category> opCategory = categoryRepository.findByDescriptionIgnoreCase(categoryDTO.description());
        if (opCategory.isPresent())
            throw new RuntimeException("Category's description already exists. Choose another.");

        // Category not exists
        opCategory = categoryRepository.findByIdAndActiveTrue(id);
        if (opCategory.isEmpty())
            throw new RuntimeException("Category not exists.");


        // Update category
        Category category = opCategory.get();
        category.update(categoryDTO);
        return categoryRepository.save(category);
    }

    public Category getCategoryById(Long id) {
        // Validations
        // Category not exists
        Optional<Category> opCategory = categoryRepository.findByIdAndActiveTrue(id);
        if (opCategory.isEmpty())
            throw new RuntimeException("Category not exists.");

        // Get category
        return opCategory.get();
    }
}
