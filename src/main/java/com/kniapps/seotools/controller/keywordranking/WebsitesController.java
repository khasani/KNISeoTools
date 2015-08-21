package com.kniapps.seotools.controller.keywordranking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kniapps.seotools.model.Site;
import com.kniapps.seotools.service.ISitesService;

@Controller
public class WebsitesController {
    
    
    @Autowired
    private ISitesService sitesService;
    
    @RequestMapping(value="keyword-ranking/websites", method=RequestMethod.GET)
    public String showWebsitesList(Model model){
               
        // Get Websites list for current user
        List<Site> liste = sitesService.searchSites();
        
        // Add list to the model
        model.addAttribute("websitesList",liste);
        
        return "keyword-ranking/websites";
    }
    
    @RequestMapping(value="keyword-ranking/websites", method=RequestMethod.POST)
    //public String addNewWebsite(@RequestParam("name") String sName,@RequestParam("url") String sURL,@RequestParam("category") String sCategory,@RequestParam("keywords") String sKeywords,Model model){
    public String addNewWebsite(Model model){
                       
        //model.addAttribute("name",sName);
        
        return "keyword-ranking/websites";
    }
    
    public ISitesService getSitesService() {
        return sitesService;
    }

    public void setSitesService( ISitesService sitesService ) {
        this.sitesService = sitesService;
    }

}
