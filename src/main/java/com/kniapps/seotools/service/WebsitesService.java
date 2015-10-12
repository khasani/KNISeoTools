package com.kniapps.seotools.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.validator.routines.UrlValidator;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.kniapps.seotools.Tools;
import com.kniapps.seotools.dao.ICategoryDao;
import com.kniapps.seotools.dao.ISearchEngineDao;
import com.kniapps.seotools.dao.ISiteDao;
import com.kniapps.seotools.model.Category;
import com.kniapps.seotools.model.Keyword;
import com.kniapps.seotools.model.SearchEngine;
import com.kniapps.seotools.model.Site;


public class WebsitesService implements IWebsitesService {

    @Autowired
    private ISiteDao siteDao;
    
    @Autowired
    private ICategoryDao categoryDao;
    
    @Autowired
    private ISearchEngineDao searchEngineDao;
    
    /*********  SITES  ***********************************/
    
    @Transactional(readOnly=true)
    public List<Site> listSites() {
       
        return siteDao.list();
    }
    
    @Transactional
    public ResponseAddWebsite createSite(String sName,String sURL,String sCategory,String sKeywords,String sSearchEngine){
               
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
            Category category = categoryDao.find(sCategory);
            if (category == null)
            {
                category = new Category(sCategory);
            }
            newSite.setCategory(category);
        }
        
        // SearchEngine Management
        SearchEngine searchEngine = searchEngineDao.find(sSearchEngine);
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
                siteDao.add(newSite);
                
            } catch ( Exception e ) {
                response.success = false;
                response.message += " Error when adding site in the database. ";   
            }
        }
        
        return response;
    }
    
    @Transactional
    public ResponseAddWebsite editSite(String sName,String sURL,String sCategory,String sKeywords,String sSearchEngine,long id)
    {
                                        
        ResponseAddWebsite response = new ResponseAddWebsite();
        
        try {
            
            // Load site
            Site site = siteDao.find(id);           
            
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
                Category category = categoryDao.find(sCategory);
                if (category == null)
                {
                    category = new Category(sCategory);
                }
                site.setCategory(category);
            }
                   
            // SearchEngine Management
            SearchEngine searchEngine = searchEngineDao.find(sSearchEngine);
            if (searchEngine == null)
            {
                response.success = false;
                response.searchEngineError = true;
                response.message += "Search engine not found. ";
            }else{
                ///searchEngine.setSite(site);
                site.setSearchEngine(searchEngine);
            }
            
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
                
                // Removing keywords if they are deleted
                for (Iterator<Keyword> it_old = old_keywords.iterator(); it_old.hasNext(); ) 
                {         
                    Keyword old_keyword = it_old.next();

                    if (!Tools.isKeywordInList(old_keyword, new_keywords)) it_old.remove();
                }
 
            }
            
            // Updating site if the fiels are OK
            if(response.success == true)
            {
                try {
                    siteDao.update(site);
                    
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
    
    @Transactional
    public ResponseBoolean removeSite(long id) {
        
        ResponseBoolean response = new ResponseBoolean();
        
        try {
            siteDao.remove(id);
            response.success = true;
        } catch ( Exception e ) {
            // TODO Auto-generated catch block
            response.success = false;
            response.message = "Error when deleting website or dependencies !";
            e.printStackTrace();
        }
        
        return response;
    }
    
    @Transactional
    public ResponseGetWebsiteDetails loadSiteDetails( long id ){
        
        ResponseGetWebsiteDetails response = new ResponseGetWebsiteDetails();
        
        // Get the list of all existing categories
        try {
            Site site = siteDao.find(id);
            
            // Loading Keywords (LAZY)
            Hibernate.initialize(site.getKeywords());
            
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
    
    @Transactional
    public Site loadFullSiteById( long id ){
        
        Site site = siteDao.find(id);
        
        // Loading Keywords (LAZY)
        Hibernate.initialize(site.getKeywords());
        
        // Loading Notes (LAZY)
        Hibernate.initialize(site.getNotes());
        
        // Loading Runs (LAZY)
        Hibernate.initialize(site.getRuns());
        
        return site;
    }
    
    /*********  CATEGORY  ***********************************/
    
    @Transactional(readOnly=true)
    public List<Category> listCategories() {
       
        return categoryDao.list();
    }
    
    /*********  SearchEngines  ***********************************/
    
    @Transactional(readOnly=true)
    public List<SearchEngine> listSearchEngines() {
       
        return searchEngineDao.list();
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

    
}
