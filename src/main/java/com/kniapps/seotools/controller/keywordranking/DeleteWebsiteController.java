package com.kniapps.seotools.controller.keywordranking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kniapps.seotools.service.IWebsitesService;

@Controller
public class DeleteWebsiteController {

    @Autowired
    private IWebsitesService sitesService;
    
    @RequestMapping(value="keyword-ranking/deleteWebsite", method=RequestMethod.POST)
    public @ResponseBody ResponseDelete deleteWebsite(@RequestParam("delete_site_id") long siteID)
    {
        ResponseDelete response = new ResponseDelete();
        
        try {
            sitesService.removeSite(siteID);
            response.success = true;
        } catch ( Exception e ) {
            // TODO Auto-generated catch block
            response.success = false;
            response.message = "Error when deleting website or dependencies !";
            e.printStackTrace();
        }
        
        return response;
    }
    
    public IWebsitesService getSitesService() {
        return sitesService;
    }

    public void setSitesService( IWebsitesService sitesService ) {
        this.sitesService = sitesService;
    }

}
