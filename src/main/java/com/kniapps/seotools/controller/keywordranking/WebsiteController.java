package com.kniapps.seotools.controller.keywordranking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.validator.routines.UrlValidator;
import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kniapps.seotools.Tools;
import com.kniapps.seotools.controller.keywordranking.AddEditWebsiteController.ResponseAddWebsite;
import com.kniapps.seotools.dao.IUserDao;
import com.kniapps.seotools.model.Category;
import com.kniapps.seotools.model.SearchEngine;
import com.kniapps.seotools.model.Site;
import com.kniapps.seotools.model.Keyword;
import com.kniapps.seotools.model.User;
import com.kniapps.seotools.service.IWebsitesService;

@Controller
public class WebsiteController {
    
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
        ResponseGetWebsiteDetails response = new ResponseGetWebsiteDetails();
        
        // Get the list of all existing categories
        try {
            Site site = sitesService.findSite(siteID);
            response.success = true;
            
            response.name = site.getName();
            response.category = site.getCategory().getName();
            response.url = site.getUrl();
            response.keywords = Tools.convertKeywords(site.getKeywords());
            response.searchEngine = site.getSearchEngine().getUrl();
            
        } catch ( Exception e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response.success = false;
            response.message = "Error : Impossible to edit the website !";
        }
        
        return response;
    }
            
    public IWebsitesService getSitesService() {
        return sitesService;
    }

    public void setSitesService( IWebsitesService sitesService ) {
        this.sitesService = sitesService;
    }
    
    // JSON Response (AJAX)
    class ResponseGetWebsiteDetails{
         
        boolean success = true;
        String message = "";
        String name ="";
        String url ="";
        String category ="";
        String keywords ="";
        String searchEngine ="";
        public boolean isSuccess() {
            return success;
        }
        public void setSuccess( boolean success ) {
            this.success = success;
        }
        public String getMessage() {
            return message;
        }
        public void setMessage( String message ) {
            this.message = message;
        }
        public String getName() {
            return name;
        }
        public void setName( String name ) {
            this.name = name;
        }
        public String getUrl() {
            return url;
        }
        public void setUrl( String url ) {
            this.url = url;
        }
        public String getCategory() {
            return category;
        }
        public void setCategory( String category ) {
            this.category = category;
        }
        public String getKeywords() {
            return keywords;
        }
        public void setKeywords( String keywords ) {
            this.keywords = keywords;
        }
        public String getSearchEngine() {
            return searchEngine;
        }
        public void setSearchEngine( String searchEngine ) {
            this.searchEngine = searchEngine;
        }
        
        
    }
    
}
