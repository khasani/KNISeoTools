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

import com.kniapps.seotools.Tools;
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
            
    public IWebsitesService getSitesService() {
        return sitesService;
    }

    public void setSitesService( IWebsitesService sitesService ) {
        this.sitesService = sitesService;
    }
    
}
