package org.example.blogplatform.controllers;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.blogplatform.Services.CategoryService;
import org.example.blogplatform.domain.dtos.CategoryDto;
import org.example.blogplatform.domain.dtos.CreateCategoryRequest;
import org.example.blogplatform.domain.entities.Category;
import org.example.blogplatform.mappers.CategoryMapper;
import org.example.blogplatform.repostries.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories() {
        List<CategoryDto> categories = categoryService.listCategories()
                .stream()
                .map(categoryMapper::toDto)
                .toList();
        System.out.println(categoryService.listCategories());
       return ResponseEntity.ok(categories);
    }
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest) {
        Category category = categoryService.createCategory(categoryMapper.toEntity(createCategoryRequest));
        return new ResponseEntity<>(categoryMapper.toDto(category), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
