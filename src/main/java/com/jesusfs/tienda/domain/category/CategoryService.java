package com.jesusfs.tienda.domain.category;

import com.jesusfs.tienda.domain.category.dto.CreateCategoryDTO;
import com.jesusfs.tienda.domain.category.dto.UpdateCategoryDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;

    public Category createCategory(@Valid CreateCategoryDTO categoryDTO) {
        // Validations
        validateDescription(categoryDTO.description());

        // Create category
        Category category = new Category(categoryDTO);
        return categoryRepository.save(category);
    }

    public List<Category> getCategories() {
        return categoryRepository.findByActiveTrue();
    }

    public void deleteCategory(Long id) {
        Category category = getCategoryById(id);
        category.setActive(false);
        categoryRepository.save(category);
    }

    public Category updateCategory(Long id, @Valid UpdateCategoryDTO categoryDTO) {
        // Validations
        validateDescription(id, categoryDTO.description());

        // Update category
        Category category = getCategoryById(id);
        category.update(categoryDTO);
        return categoryRepository.save(category);
    }

    public Category getCategoryById(Long id) {
        if (id == null) return null;
        // Category not exists
        Optional<Category> opCategory = categoryRepository.findByIdAndActiveTrue(id);
        if (opCategory.isEmpty()) throw new RuntimeException("Category not exists.");

        return opCategory.get();
    }

    // Validations
    private void validateDescription(Long id, String description) {
        Optional<Category> opCategory;
        if (id == null) opCategory = categoryRepository.findByDescriptionIgnoreCase(description);
        else opCategory = categoryRepository.findByDescriptionIgnoreCaseAndIdNot(description, id);

        if (opCategory.isPresent()) throw new RuntimeException("Category's description already exists. Choose another.");
    }

    private void validateDescription(String description) { validateDescription(null, description); }
}
