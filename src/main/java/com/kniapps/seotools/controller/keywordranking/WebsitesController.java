package com.kniapps.seotools.controller.keywordranking;

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
        if(sName.isEmpty())
        {
            response.success = false;
            response.nameError = true;
            response.message += " The site name can't be empty. ";
        }
        else newSite.setName(sName);
        
        // URL Management
        final String[] schemes={"http","https"};
        final UrlValidator urlValidator=new UrlValidator(schemes);
        if (urlValidator.isValid(sURL)) newSite.setUrl(sURL);
        else {
            response.success = false;
            response.urlError = true;
            response.message += " URL is not in the good format. ";
        }
        
        // Category Management
        if(sCategory.isEmpty())
        {
            response.success = false;
            response.categoryError = true;
            response.message += " The category can't be empty. ";
        }else
        {
            Category category = sitesService.findCategory(sCategory);
            if (category == null)
            {
                category = new Category(sCategory);
            }
            newSite.setCategory(category);
        }
        
        // SearchEngine Management
        SearchEngine searchEngine = sitesService.findSearchEngine(sSearchEngine);
        if (searchEngine == null)
        {
            response.success = false;
            response.searchEngineError = true;
            response.message += "Search engine not found. ";
        }else newSite.setSearchEngine(searchEngine);
        
        // Keyword Management
        HashSet<Keyword> list_keywords = convertKeywords(sKeywords,newSite);
        if (list_keywords.isEmpty())
        {
            response.success = false;
            response.keywordsError = true;
            response.message += " Keywords field can't be empty. ";
            
        }else newSite.setKeywords(list_keywords);
        
        if(response.success == true)
        {
        
            try {
                sitesService.addSite(newSite);
                
            } catch ( Exception e ) {
                response.success = false;
                response.message += " Error when adding site in the database. ";   
            }
        }
        
        return response;
    }
    
    @RequestMapping(value="keyword-ranking/deleteWebsite", method=RequestMethod.GET)
    public @ResponseBody ResponseAddWebsite deleteWebsite(@RequestParam("id") String sID)
    {
        ResponseAddWebsite response = new ResponseAddWebsite();
        
        
        return response;
    }
    
    @RequestMapping(value="keyword-ranking/importKeywords", method=RequestMethod.GET)
    public @ResponseBody String importKeywords(@RequestParam("id") long siteID)
    {
        
        // Get the list of all existing categories
        List<Keyword> list = sitesService.findKeywords(siteID);
        
        String sReturn = convertKeywords(list);
        
        return sReturn;
    }
            
    public ISitesService getSitesService() {
        return sitesService;
    }

    public void setSitesService( ISitesService sitesService ) {
        this.sitesService = sitesService;
    }
    
    // JSON Response (AJAX)
    class ResponseAddWebsite{
         
        boolean success = true;
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
    
    // Tool Functions
    
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
    
    private String convertKeywords(List<Keyword> list_keywords)
    {
        String sReturn = "";
        
        if (list_keywords != null)
        {
            for(int i = 0 ; i<list_keywords.size(); i++)
            {
                sReturn += list_keywords.get(i).getName();
                
                if(i < list_keywords.size()-1) sReturn += ",";
            }
        }
        
        return sReturn;
    }

}
