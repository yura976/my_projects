package org.example.controllers;

import org.example.dto.CategoryDto;
import org.example.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getNewsById(@PathVariable Long id) { return categoryService.getById(id); }

    @GetMapping
    public ResponseEntity getAllNews() {
        return categoryService.getAll();
    }

    @PostMapping
    public ResponseEntity createNews(@RequestBody CategoryDto categoryDto) {
        return categoryService.create(categoryDto);
    }

    @PutMapping
    public ResponseEntity updateNews(@RequestBody CategoryDto categoryDto) { return categoryService.update(categoryDto); }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteNews(@PathVariable Long id) {
        return categoryService.delete(id);
    }

}
