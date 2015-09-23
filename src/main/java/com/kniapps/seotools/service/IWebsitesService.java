package com.kniapps.seotools.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kniapps.seotools.model.Category;
import com.kniapps.seotools.model.Keyword;
import com.kniapps.seotools.model.SearchEngine;
import com.kniapps.seotools.model.Site;

public interface IWebsitesService {

    // Sites
    List<Site> listSites();
    void addSite(Site site) throws Exception;
    void updateSite(Site site) throws Exception;
    void removeSite(long id) throws Exception;
    Site loadFullSiteById(long id) throws Exception;
    Site loadSiteById(long id) throws Exception;
    
    // Categories
    Category findCategory(String sCategory);
    List<Category> listCategories();
    
    // Search Engine
    SearchEngine findSearchEngine(String sSearchEngine);
    List<SearchEngine> listSearchEngines();
    
    // Keywords
    List<Keyword> findKeywords(long siteID);
}
