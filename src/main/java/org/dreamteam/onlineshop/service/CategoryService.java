package org.dreamteam.onlineshop.service;

import org.dreamteam.onlineshop.model.Category;

import java.util.List;

public interface CategoryService {

    Category addCategory(Category category);
    Category updateCategory(Long id, Category category);
    Category getCategory(Long id);
    boolean deleteCategory(Long id);

    List<Category> getAllCategories();
}
