package com.kniapps.seotools.controller.keywordranking;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kniapps.seotools.model.Run;
import com.kniapps.seotools.model.Site;
import com.kniapps.seotools.service.INotesService;
import com.kniapps.seotools.service.IRunsService;
import com.kniapps.seotools.service.IWebsitesService;

@Controller
public class RunsWebsiteController {
    
    @Autowired
    private IRunsService runsService;
    
    @Autowired
    private IWebsitesService websiteService;
    
    @Autowired
    private INotesService notesService;

    @RequestMapping(value="keyword-ranking/website", method=RequestMethod.GET)
    public String showKeywordRankingDashboard(Model model, @RequestParam("id") long siteID){
        
        try {
            // Get site
            Site site = websiteService.loadFullSiteById(siteID);
            model.addAttribute("site", site);
            
            // Get runs
            //List<Run> list_runs = runsService.findRunsLastMonth(siteID);
            //model.addAttribute("runs", list_runs);   
            
            
        } catch ( Exception e1 ) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        return "keyword-ranking/website";
    }
    
    @RequestMapping(value="keyword-ranking/launchRun", method=RequestMethod.POST)
    public @ResponseBody Run addRun(Model model, @RequestParam("id") long siteID){
           
        return runsService.launchRun(siteID);
    }

    public IRunsService getRunsService() {
        return runsService;
    }

    public void setRunsService( IRunsService runsService ) {
        this.runsService = runsService;
    }

    public IWebsitesService getWebsiteService() {
        return websiteService;
    }

    public void setWebsiteService( IWebsitesService websiteService ) {
        this.websiteService = websiteService;
    }

    public INotesService getNotesService() {
        return notesService;
    }

    public void setNotesService( INotesService notesService ) {
        this.notesService = notesService;
    }
    

}
