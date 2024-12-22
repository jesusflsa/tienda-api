package com.jesusfs.tienda.controller;

import com.jesusfs.tienda.domain.category.Category;
import com.jesusfs.tienda.domain.category.CategoryServiceImpl;
import com.jesusfs.tienda.domain.category.dto.CreateCategoryDTO;
import com.jesusfs.tienda.domain.category.dto.ResponseCategoryDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {
    private CategoryServiceImpl categoryService;

    @GetMapping("/{id}")
    public ResponseCategoryDTO getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        return new ResponseCategoryDTO(category);
    }

    @GetMapping
    public Page<ResponseCategoryDTO> getCategories(@PageableDefault Pageable page) {
        Page<Category> categories = categoryService.getCategories(page);
        return categories.map(ResponseCategoryDTO::new);
    }

    @GetMapping(params = "query")
    public List<ResponseCategoryDTO> getCategoriesFromQuery(@RequestParam(name = "query", required = false) String query) {
        if (query.isBlank()) throw new RuntimeException("Invalid query");

        List<Category> categories = categoryService.getCategoriesByQuery(query);
        return categories.stream().map(ResponseCategoryDTO::new).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseCategoryDTO createCategory(@RequestBody @Valid CreateCategoryDTO categoryDTO) {
        Category category = categoryService.createCategory(categoryDTO);
        return new ResponseCategoryDTO(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {
        boolean status = categoryService.deleteCategory(id);
        return status ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
