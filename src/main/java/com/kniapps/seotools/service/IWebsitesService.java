package com.kniapps.seotools.service;

import java.util.List;

import com.kniapps.seotools.model.Category;
import com.kniapps.seotools.model.SearchEngine;
import com.kniapps.seotools.model.Site;


public interface IWebsitesService {

    // Sites
    List<Site> listSites();
    ResponseAddWebsite createSite(String sName,String sURL,String sCategory,String sKeywords,String sSearchEngine);
    ResponseAddWebsite editSite(String sName,String sURL,String sCategory,String sKeywords,String sSearchEngine,long id);
    ResponseBoolean removeSite(long id);
    Site loadFullSiteById(long id);
    ResponseGetWebsiteDetails loadSiteDetails(long id);
    
    // Categories
    List<Category> listCategories();
    
    // Search Engine
    List<SearchEngine> listSearchEngines();
    
}
