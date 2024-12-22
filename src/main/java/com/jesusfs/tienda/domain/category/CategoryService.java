package com.jesusfs.tienda.domain.category;

import com.jesusfs.tienda.domain.category.dto.CreateCategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    Category getCategoryById(Long id);
    Page<Category> getCategories(Pageable page);
    List<Category> getCategoriesByQuery(String query);

    Category createCategory(CreateCategoryDTO categoryDTO);

    boolean deleteCategory(Long id);
}
