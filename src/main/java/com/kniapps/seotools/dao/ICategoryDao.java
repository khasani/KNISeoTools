package com.kniapps.seotools.dao;

import com.kniapps.seotools.model.Category;


public interface ICategoryDao extends GenericDao<Category, Long> {
    
    public Category find(String name);

}
