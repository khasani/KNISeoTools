package com.kniapps.seotools.controller.keywordranking;

import java.util.ArrayList;
import java.util.Date;







import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kniapps.seotools.model.Keyword;
import com.kniapps.seotools.model.Position;
import com.kniapps.seotools.model.Run;
import com.kniapps.seotools.model.Site;
import com.kniapps.seotools.service.INotesService;
import com.kniapps.seotools.service.IRunsService;
import com.kniapps.seotools.service.IWebsitesService;
import com.kniapps.seotools.service.ResponseBoolean;

import java.util.List;

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
            // Load site
            Site site = websiteService.loadFullSiteById(siteID);
            model.addAttribute("site", site);
            
            // Get the Position List in a 2 dimentional array to show easily in JPS file
            ArrayList<ArrayList<Object>> list = runsService.findRunsPositionsLastDays(siteID, 1500);
            model.addAttribute("positions_array", list); 
     
            
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
    
    @RequestMapping(value="keyword-ranking/deleteRun", method=RequestMethod.POST)
    public @ResponseBody ResponseBoolean deleteNote(@RequestParam("delete_run_id") long runID)
    {
        ResponseBoolean response = new ResponseBoolean();
        
        try {
            runsService.removeRun(runID);
            response.setSuccess( true );
        } catch ( Exception e ) {
            // TODO Auto-generated catch block
            response.setSuccess( false );         
            response.setMessage( "Error when deleting selected run !" );
            e.printStackTrace();
        }
        
        return response;
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
