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
import com.kniapps.seotools.dao.IKeywordDao;
import com.kniapps.seotools.dao.ISearchEngineDao;
import com.kniapps.seotools.dao.ISiteDao;
import com.kniapps.seotools.dao.SiteDao;
import com.kniapps.seotools.model.Category;
import com.kniapps.seotools.model.Keyword;
import com.kniapps.seotools.model.SearchEngine;
import com.kniapps.seotools.model.Site;
import com.kniapps.seotools.model.User;


public class WebsitesService implements IWebsitesService {

    @Autowired
    private ISiteDao siteDao;
    
    @Autowired
    private ICategoryDao categoryDao;
    
    @Autowired
    private ISearchEngineDao searchEngineDao;
    
    @Autowired
    private IKeywordDao keywordDao;
    
    /*********  SITES  ***********************************/
    
    @Transactional(readOnly=true)
    public List<Site> listSites() {
       
        return siteDao.list();
    }
    
    @Transactional
    public void addSite(Site site) throws Exception {
              
        siteDao.add(site);
    }
    
    @Transactional
    public void removeSite(long id) throws Exception {
        
        siteDao.remove(id);
    }
    
    @Transactional
    public Site findSite( long id ) throws Exception {
        
        return siteDao.find(id);
    }
    
    /*********  CATEGORY  ***********************************/
    
    @Transactional(readOnly=true)
    public Category findCategory(String sCategory) {
       
        return categoryDao.find(sCategory);
    }
    
    @Transactional(readOnly=true)
    public List<Category> listCategories() {
       
        return categoryDao.list();
    }
    
    /*********  SearchEngines  ***********************************/
    
    @Transactional(readOnly=true)
    public SearchEngine findSearchEngine(String sSearchEngine) {
       
        return searchEngineDao.find(sSearchEngine);
    }
    
    @Transactional(readOnly=true)
    public List<SearchEngine> listSearchEngines() {
       
        return searchEngineDao.list();
    }
    
    /*********  Keywords  ***********************************/
    
    @Transactional(readOnly=true)
    public List<Keyword> findKeywords(long siteID) {
       
        return keywordDao.findKeywords(siteID);
    }
      
    /********* Getters / Setters  *******************************/

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

    public ISearchEngineDao getSearchEngineDao() {
        return searchEngineDao;
    }

    public void setSearchEngineDao( ISearchEngineDao searchEngineDao ) {
        this.searchEngineDao = searchEngineDao;
    }

    public IKeywordDao getKeywordDao() {
        return keywordDao;
    }

    public void setKeywordDao( IKeywordDao keywordDao ) {
        this.keywordDao = keywordDao;
    } 



}
