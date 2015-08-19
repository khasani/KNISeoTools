package com.kniapps.seotools.controller.keywordranking;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OptionsController {

    @RequestMapping("/keyword-ranking/options")
    public String showKeywordRankingOptions(Model model){
        
        //SiteDao siteDao = new SiteDao();
        
        //List<Site> liste = siteDao.searchSites("alex");
        //SitesService sitesService = new SitesService();
        
        return "keyword-ranking/options";
    }

}
