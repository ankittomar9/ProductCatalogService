package com.company.productcatalogservice.services;

import com.company.productcatalogservice.models.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getAllCategories();
    

    Category getCategoryById(Long categoryId);

    Category save(Category input);

    Category replaceCategory(Long id, Category categoryRequest);
}
