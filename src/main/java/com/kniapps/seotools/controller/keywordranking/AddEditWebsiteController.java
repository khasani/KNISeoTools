package com.kniapps.seotools.controller.keywordranking;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.validator.routines.UrlValidator;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kniapps.seotools.Tools;
import com.kniapps.seotools.model.Category;
import com.kniapps.seotools.model.Keyword;
import com.kniapps.seotools.model.SearchEngine;
import com.kniapps.seotools.model.Site;
import com.kniapps.seotools.service.IWebsitesService;

@Controller
public class AddEditWebsiteController {

    @Autowired
    private IWebsitesService sitesService;
        
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
        sKeywords= sKeywords.replaceAll("\\s","");
        HashSet<Keyword> list_keywords = Tools.convertKeywords(sKeywords,newSite);
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
    
    @RequestMapping(value="keyword-ranking/editWebsite", method=RequestMethod.POST)
    public @ResponseBody ResponseAddWebsite editWebsite(@RequestParam("name") String sName,@RequestParam("url") String sURL,@RequestParam("category") String sCategory,@RequestParam("keywords") String sKeywords,@RequestParam("search_engine") String sSearchEngine,@RequestParam("form_add_site_id") long id){
                       
        ResponseAddWebsite response = new ResponseAddWebsite();
        
        try {
            
            // Load site
            Site site = sitesService.findSite(id);
            
            // Name
            if(sName.isEmpty())
            {
                response.success = false;
                response.nameError = true;
                response.message += " The site name can't be empty. ";
            }
            else site.setName(sName);
            
            // URL Management
            final String[] schemes={"http","https"};
            final UrlValidator urlValidator=new UrlValidator(schemes);
            if (urlValidator.isValid(sURL)) site.setUrl(sURL);
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
                site.setCategory(category);
            }
                   
            // SearchEngine Management
            SearchEngine searchEngine = sitesService.findSearchEngine(sSearchEngine);
            if (searchEngine == null)
            {
                response.success = false;
                response.searchEngineError = true;
                response.message += "Search engine not found. ";
            }else site.setSearchEngine(searchEngine);
            
            // Keyword Management
            sKeywords= sKeywords.replaceAll("\\s","");
            Set<Keyword> new_keywords = Tools.convertKeywords(sKeywords,site);
            Set<Keyword> old_keywords = site.getKeywords();
            if (new_keywords.isEmpty())
            {
                response.success = false;
                response.keywordsError = true;
                response.message += " Keywords field can't be empty. ";
                
            }else{
                             
                // Adding new keywords if they doesn't exists
                for (Iterator<Keyword> it_new = new_keywords.iterator(); it_new.hasNext(); ) 
                {         
                    Keyword new_keyword = it_new.next();

                    if (!Tools.isKeywordInList( new_keyword, old_keywords)) old_keywords.add(new_keyword);    
                }
                
                int i=0;
                
                // Removing keywords if they are deleted
                for (Iterator<Keyword> it_old = old_keywords.iterator(); it_old.hasNext(); ) 
                {         
                    Keyword old_keyword = it_old.next();

                    if (!Tools.isKeywordInList(old_keyword, new_keywords)) old_keywords.remove(old_keyword);    
                }
 
                //site.setKeywords(old_keywords);
            }
            
            // Updating site if the fiels are OK
            if(response.success == true)
            {
                try {
                    sitesService.updateSite(site);
                    
                } catch ( Exception e ) {
                    response.success = false;
                    response.message += " Error when updating site in the database. ";   
                }
            }
            
        } catch ( Exception e ) {
            
            response.success = false;
            response.message += " Error when updating site in the database. ";
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

}
