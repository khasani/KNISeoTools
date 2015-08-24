package com.kniapps.seotools.dao;

import java.util.List;

import com.kniapps.seotools.model.Category;

public interface ICategoryDao {
    
    public Category searchCategory(String name);
    public List<Category> listCategories();
    public void addCategory(Category category);
}
