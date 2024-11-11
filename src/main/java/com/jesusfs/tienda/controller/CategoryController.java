package com.jesusfs.tienda.controller;

import com.jesusfs.tienda.model.Category;
import com.jesusfs.tienda.dto.category.CreateCategoryDTO;
import com.jesusfs.tienda.dto.category.ResponseCategoryDTO;
import com.jesusfs.tienda.dto.category.UpdateCategoryDTO;
import com.jesusfs.tienda.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
@PreAuthorize("denyAll()")
public class CategoryController {

    CategoryService categoryService;

    @PostMapping
    @PreAuthorize("hasAnyRole({'ADMIN', 'DEVELOPER'})")
    public ResponseCategoryDTO createCategory(@RequestBody @Valid CreateCategoryDTO categoryDTO) {
        Category category = categoryService.createCategory(categoryDTO);
        return new ResponseCategoryDTO(category);
    }

    @GetMapping
    @PreAuthorize("permitAll()")
    public List<ResponseCategoryDTO> getCategories() {
        List<Category> categories = categoryService.getCategories();
        return categories.stream().map(ResponseCategoryDTO::new).toList();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole({'DEVELOPER'})")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole({'ADMIN', 'DEVELOPER'})")
    public ResponseEntity<ResponseCategoryDTO> updateCategory(@PathVariable Long id, @RequestBody @Valid UpdateCategoryDTO categoryDTO) {
        Category category = categoryService.updateCategory(id, categoryDTO);
        return ResponseEntity.ok(new ResponseCategoryDTO(category));
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseCategoryDTO getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        return new ResponseCategoryDTO(category);
    }
}
