package com.kniapps.seotools.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kniapps.seotools.model.Category;

public class CategoryDao implements ICategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Category searchCategory( String name ) {
        Query hqlQuery = getSessionFactory().getCurrentSession().createQuery("from Category where name = ?");
        List<Category> cat_list = hqlQuery.setString(0,name).list();
        
        if (cat_list.isEmpty()) return null;
        else return cat_list.get(0);
    }
    
    public List<Category> listCategories() {
        Query hqlQuery = getSessionFactory().getCurrentSession().createQuery("from Category");
        return hqlQuery.list();
    }
    
    public void addCategory(Category category) {
        
        getSessionFactory().getCurrentSession().save(category);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory( SessionFactory sessionFactory ) {
        this.sessionFactory = sessionFactory;
    }




}
