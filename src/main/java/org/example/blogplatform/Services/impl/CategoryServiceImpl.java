package org.example.blogplatform.Services.impl;

import lombok.RequiredArgsConstructor;
import org.example.blogplatform.Services.CategoryService;
import org.example.blogplatform.domain.entities.Category;
import org.example.blogplatform.repostries.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAllWithPostCount();
    }

    @Override
    public Category createCategory(Category category) {

        if(categoryRepository.existsByName(category.getName())) {
            throw new IllegalArgumentException("Category name already exists:= " + category.getName());
        }

        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(UUID id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()) {
            if(!category.get().getPosts().isEmpty()) {
                throw new IllegalArgumentException("Category has posts:= " + id);
            }
            categoryRepository.deleteById(id);
        }else {
            throw new IllegalArgumentException("Category not found:= " + id);
        }
    }
}
