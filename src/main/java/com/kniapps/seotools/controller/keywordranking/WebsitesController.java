package com.kniapps.seotools.controller.keywordranking;

import java.util.HashSet;
import java.util.List;

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

import com.kniapps.seotools.dao.IUserDao;
import com.kniapps.seotools.model.Category;
import com.kniapps.seotools.model.SearchEngine;
import com.kniapps.seotools.model.Site;
import com.kniapps.seotools.model.Keyword;
import com.kniapps.seotools.model.User;
import com.kniapps.seotools.service.ISitesService;

@Controller
public class WebsitesController {
    
    @Autowired
    private ISitesService sitesService;
    
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
    
    @RequestMapping(value="keyword-ranking/websites", method=RequestMethod.POST)
    public @ResponseBody ResponseAddWebsite addNewWebsite(@RequestParam("name") String sName,@RequestParam("url") String sURL,@RequestParam("category") String sCategory,@RequestParam("keywords") String sKeywords,@RequestParam("search_engine") String sSearchEngine){
                       
        ResponseAddWebsite response = new ResponseAddWebsite();
        
        // Site creation
        Site newSite = new Site();
        newSite.setName(sName);
        newSite.setUrl(sURL);
        
        // Category Management
        Category category = sitesService.findCategory(sCategory);
        if (category == null)
        {
            category = new Category(sCategory);
        }
        newSite.setCategory(category);
        
        // SearchEngine Management
        SearchEngine searchEngine = sitesService.findSearchEngine(sSearchEngine);
        if (searchEngine == null)
        {
            // Error
        }
        newSite.setSearchEngine(searchEngine);
        
        // Keyword Management
        HashSet<Keyword> list_keywords = convertKeywords(sKeywords,newSite);
        newSite.setKeywords(list_keywords);
        
        try {
            sitesService.addSite(newSite);
        } catch ( Exception e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        response.message="Fields in red are not in the correct format !";
        response.success = false;
        response.nameError = true;
        response.categoryError = true;
        response.keywordsError = true;
        response.urlError = true;
        
        return response;
    }
    
    @RequestMapping(value="keyword-ranking/deleteWebsite", method=RequestMethod.GET)
    public @ResponseBody ResponseAddWebsite deleteWebsite(@RequestParam("id") String sID)
    {
        ResponseAddWebsite response = new ResponseAddWebsite();
        
        
        return response;
    }
            
    public ISitesService getSitesService() {
        return sitesService;
    }

    public void setSitesService( ISitesService sitesService ) {
        this.sitesService = sitesService;
    }
    
    private HashSet<Keyword> convertKeywords(String sKeywords, Site site)
    {
        HashSet<Keyword> list_keywords = new HashSet<Keyword>(0);
        
        String[] tab_keywords = sKeywords.split(",");
        for(int i=0 ; i<tab_keywords.length ; i++)
        {
            if (! tab_keywords[i].equals(""))
            {
                list_keywords.add( new Keyword(tab_keywords[i],site));
            }
        }
        return list_keywords;
    }
    
    // JSON Response (AJAX)
    class ResponseAddWebsite{
         
        boolean success = false;
        String message = "";
        boolean nameError = false;
        boolean urlError = false;
        boolean keywordsError = false;
        boolean categoryError = false;
        boolean searchEngineError = false;
        
        public ResponseAddWebsite() {
        }

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

        public boolean isNameError() {
            return nameError;
        }

        public void setNameError( boolean nameError ) {
            this.nameError = nameError;
        }

        public boolean isUrlError() {
            return urlError;
        }

        public void setUrlError( boolean urlError ) {
            this.urlError = urlError;
        }

        public boolean isKeywordsError() {
            return keywordsError;
        }

        public void setKeywordsError( boolean keywordsError ) {
            this.keywordsError = keywordsError;
        }

        public boolean isCategoryError() {
            return categoryError;
        }

        public void setCategoryError( boolean categoryError ) {
            this.categoryError = categoryError;
        }

        public boolean isSearchEngineError() {
            return searchEngineError;
        }

        public void setSearchEngineError( boolean searchEngineError ) {
            this.searchEngineError = searchEngineError;
        }
        
    }

}
