package com.jesusfs.tienda.domain.category;

import com.jesusfs.tienda.domain.category.dto.CreateCategoryDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long id) {
        Optional<Category> opCategory = categoryRepository.findByIdAndActiveTrue(id);
        return opCategory.orElseThrow(() -> new RuntimeException("Not category found"));
    }

    @Override
    public Page<Category> getCategories(Pageable page) {
        return categoryRepository.findByActiveTrue(page);
    }

    @Override
    public List<Category> getCategoriesByQuery(String query) {
        return categoryRepository.getCategoriesFromQuery(query);
    }

    @Override
    public Category createCategory(@Valid CreateCategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.name());
        return categoryRepository.save(category);
    }

    @Override
    public boolean deleteCategory(Long id) {
        Optional<Category> opCategory = categoryRepository.findByIdAndActiveTrue(id);
        if (opCategory.isEmpty()) return false;

        Category category = opCategory.get();
        category.setActive(false);
        categoryRepository.save(category);
        return true;
    }
}
