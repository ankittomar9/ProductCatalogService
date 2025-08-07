package com.company.productcatalogservice.controllers;

import com.company.productcatalogservice.dtos.CategoryDto;
import com.company.productcatalogservice.models.Category;
import com.company.productcatalogservice.services.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

   // @Autowired
   // @Qualifier("categoryService") // Assuming your primary CategoryService bean is named "categoryService"
    private ICategoryService categoryService;

    @GetMapping()
    public List<CategoryDto> getAllCategories() {
        List<CategoryDto> categoryDtos = new ArrayList<>();
        List<Category> categories = categoryService.getAllCategories();
        for (Category category : categories) {
            categoryDtos.add(from(category));
        }
        return categoryDtos;
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> findCategoryById(@PathVariable Long categoryId) {
        try {
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            if (categoryId <= 0) {
                headers.add("called by", "Invalid Client");
                throw new IllegalArgumentException("Category ID must be greater than 0");
            }
            Category category = categoryService.getCategoryById(categoryId);
            headers.add("called by", "Category Retrieval Service");
            if (category == null) {
                return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(from(category), headers, HttpStatus.OK);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private CategoryDto from(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        return categoryDto;
    }

    @PostMapping
    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto) {
        Category input = from(categoryDto);
        Category output = categoryService.save(input);
        return from(output);
    }

    @PutMapping("/{id}")
    public CategoryDto replaceCategory(@PathVariable Long id, @RequestBody CategoryDto request) {
        Category categoryRequest = from(request);
        Category category = categoryService.replaceCategory(id, categoryRequest);
        return from(category);
    }

    private Category from(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        return category;
    }
}