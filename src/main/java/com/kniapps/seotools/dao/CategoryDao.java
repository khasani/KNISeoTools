package com.kniapps.seotools.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.kniapps.seotools.model.Category;


public class CategoryDao extends HibernateDao<Category, Long> implements ICategoryDao {

    public Category find(String name) {
        
        Criteria cr = currentSession().createCriteria(Category.class);
        cr.add(Restrictions.eq("name", name));
        List<Category> list = cr.list();
        
        if (list.isEmpty()) return null;
        else return list.get(0);
    }

}
