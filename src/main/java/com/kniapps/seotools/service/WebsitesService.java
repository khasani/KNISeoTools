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
       
        return siteDao.listSites();
    }
    
    @Transactional
    public void addSite(Site site) throws Exception {
              
        siteDao.addSite(site);
    }
    
    @Transactional
    public void removeSite(long id) throws Exception {
        
        siteDao.removeSite(id);
    }
    
    @Transactional
    public Site findSite( long id ) throws Exception {
        
        return siteDao.findSite(id);
    }
    
    /*********  CATEGORY  ***********************************/
    
    @Transactional(readOnly=true)
    public Category findCategory(String sCategory) {
       
        return categoryDao.searchCategory(sCategory);
    }
    
    @Transactional(readOnly=true)
    public List<Category> listCategories() {
       
        return categoryDao.listCategories();
    }
    
    /*********  SearchEngines  ***********************************/
    
    @Transactional(readOnly=true)
    public SearchEngine findSearchEngine(String sSearchEngine) {
       
        return searchEngineDao.findSearchEngine(sSearchEngine);
    }
    
    @Transactional(readOnly=true)
    public List<SearchEngine> listSearchEngines() {
       
        return searchEngineDao.listSearchEngines();
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
