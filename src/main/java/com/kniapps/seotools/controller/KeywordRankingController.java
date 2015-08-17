package com.kniapps.seotools.controller;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kniapps.seotools.dao.ISiteDao;
import com.kniapps.seotools.dao.SiteDao;
import com.kniapps.seotools.model.Keyword;
import com.kniapps.seotools.model.Note;
import com.kniapps.seotools.model.Site;
import com.kniapps.seotools.service.ISitesService;
import com.kniapps.seotools.service.SitesService;

@Controller
public class KeywordRankingController {
    
    
    
    @RequestMapping("/keyword-ranking")
    public ModelAndView showKeywordRankingDashboard(){
        
        ModelAndView model = new ModelAndView("keyword-ranking");
        
        return model;
    }
    
    /*******************************************************/
    /************** WEBSITES *******************************/
    /*******************************************************/
    
    @Autowired
    private ISitesService sitesService;
    
    @RequestMapping("/keyword-ranking/websites")
    public ModelAndView showKeywordRankingWebsites(){
        
        ModelAndView model = new ModelAndView("keyword-ranking/websites");
        
        // Get Websites list for current user
        List<Site> liste = sitesService.searchSites();
        
        // Add list to the model
        model.addObject("websitesList",liste);
        
        return model;
    }
    
    public ISitesService getSitesService() {
        return sitesService;
    }

    public void setSitesService( ISitesService sitesService ) {
        this.sitesService = sitesService;
    }
    
    
    /*******************************************************/
    /************** KEYWORDS *******************************/
    /*******************************************************/
    @RequestMapping("/keyword-ranking/keywords")
    public String showKeywordRankingKeywords(){
        
        
        return "keyword-ranking/keywords";
    }
    
    @RequestMapping("/keyword-ranking/options")
    public String showKeywordRankingOptions(){
        
        //SiteDao siteDao = new SiteDao();
        
        //List<Site> liste = siteDao.searchSites("alex");
        //SitesService sitesService = new SitesService();
        
        
        
        
        return "keyword-ranking/options";
    }


    
    

}
