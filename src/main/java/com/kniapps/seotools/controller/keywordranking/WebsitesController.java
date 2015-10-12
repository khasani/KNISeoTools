package com.kniapps.seotools.controller.keywordranking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kniapps.seotools.service.ResponseAddWebsite;
import com.kniapps.seotools.model.Category;
import com.kniapps.seotools.model.SearchEngine;
import com.kniapps.seotools.model.Site;
import com.kniapps.seotools.service.IWebsitesService;
import com.kniapps.seotools.service.ResponseGetWebsiteDetails;

@Controller
public class WebsitesController {
    
    @Autowired
    private IWebsitesService sitesService;
    
    // Page Websites List
    @RequestMapping(value="keyword-ranking/websites", method=RequestMethod.GET)
    public String showWebsitesList(Model model){
               
        // Get Websites list for current user
        List<Site> list_sites = sitesService.listSites();
        
        // Get the list of all existing categories
        List<Category> list_categories = sitesService.listCategories();
        
        // Get the list of all existing search engines
        List<SearchEngine> list_searchEngines = sitesService.listSearchEngines();
        
        // Add lists to the model
        model.addAttribute("websitesList",list_sites);
        model.addAttribute("categoriesList",list_categories);
        model.addAttribute("searchEnginesList",list_searchEngines);
        
        return "keyword-ranking/websites";
    }
    
    // AJAX - Get Site Details
    @RequestMapping(value="keyword-ranking/getSiteDetails", method=RequestMethod.GET)
    public @ResponseBody ResponseGetWebsiteDetails getSiteDetails(@RequestParam("id") long siteID)
    { 
        
        return sitesService.loadSiteDetails(siteID);
    }
    
    @RequestMapping(value="keyword-ranking/websites", method=RequestMethod.POST)
    public @ResponseBody ResponseAddWebsite addNewWebsite(@RequestParam("name") String sName,@RequestParam("url") String sURL,@RequestParam("category") String sCategory,@RequestParam("keywords") String sKeywords,@RequestParam("search_engine") String sSearchEngine){
                       
        return sitesService.createSite(sName,sURL,sCategory,sKeywords,sSearchEngine);
    }
    
    @RequestMapping(value="keyword-ranking/editWebsite", method=RequestMethod.POST)
    public @ResponseBody ResponseAddWebsite editWebsite(@RequestParam("name") String sName,@RequestParam("url") String sURL,@RequestParam("category") String sCategory,@RequestParam("keywords") String sKeywords,@RequestParam("search_engine") String sSearchEngine,@RequestParam("form_add_site_id") long id){
                       
        return sitesService.editSite(sName,sURL,sCategory,sKeywords,sSearchEngine,id);
        
    }
            
    public IWebsitesService getSitesService() {
        return sitesService;
    }

    public void setSitesService( IWebsitesService sitesService ) {
        this.sitesService = sitesService;
    }
    
    
    
}
