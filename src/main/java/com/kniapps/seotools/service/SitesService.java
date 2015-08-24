package com.kniapps.seotools.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kniapps.seotools.dao.ICategoryDao;
import com.kniapps.seotools.dao.ISiteDao;
import com.kniapps.seotools.dao.SiteDao;
import com.kniapps.seotools.model.Category;
import com.kniapps.seotools.model.Site;
import com.kniapps.seotools.model.User;


public class SitesService implements ISitesService {

    @Autowired
    private ISiteDao siteDao;
    
    @Autowired
    private ICategoryDao categoryDao;
    
    @Transactional(readOnly=true)
    public List<Site> searchSites() {
       
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return siteDao.searchSites(userName);
    }
    
    @Transactional(readOnly=true)
    public Category searchCategory(String sCategory) {
       
        return categoryDao.searchCategory(sCategory);
    }
    
    @Transactional(readOnly=true)
    public List<Category> listCategories() {
       
        return categoryDao.listCategories();
    }
    
    @Transactional
    public void addCategory(Category category) {
        
        categoryDao.addCategory(category);
    }
    
    public void addSite( Site site ) {
              
        siteDao.addSite( site );
    }

    public ISiteDao getSiteDao() {
        return siteDao;
    }

    public void setSiteDao( ISiteDao siteDao ) {
        this.siteDao = siteDao;
    }

    public ICategoryDao getCategoryDao() {
        return categoryDao;
    }

    public void setCategoryDao( ICategoryDao categoryDao ) {
        this.categoryDao = categoryDao;
    }




}
