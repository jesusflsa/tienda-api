package com.jesusfs.tienda.controller;

import com.jesusfs.tienda.model.category.Category;
import com.jesusfs.tienda.model.category.CreateCategoryDTO;
import com.jesusfs.tienda.model.category.ResponseCategoryDTO;
import com.jesusfs.tienda.model.category.UpdateCategoryDTO;
import com.jesusfs.tienda.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public ResponseCategoryDTO createCategory(@RequestBody @Valid CreateCategoryDTO categoryDTO) {
        Category category = categoryService.createCategory(categoryDTO);
        return new ResponseCategoryDTO(category);
    }

    @GetMapping
    public List<ResponseCategoryDTO> getCategories() {
        List<Category> categories = categoryService.getCategories();
        return categories.stream().map(ResponseCategoryDTO::new).toList();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable Long id) {
        Boolean res = categoryService.deleteCategory(id);
        return res ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCategoryDTO> updateCategory(@PathVariable Long id, @RequestBody @Valid UpdateCategoryDTO categoryDTO) {
        Category category = categoryService.updateCategory(id, categoryDTO);
        return ResponseEntity.ok(new ResponseCategoryDTO(category));
    }

    @GetMapping("/{id}")
    public ResponseCategoryDTO getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        return new ResponseCategoryDTO(category);
    }
}
