package com.kniapps.seotools.controller.keywordranking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kniapps.seotools.Tools;
import com.kniapps.seotools.model.Keyword;
import com.kniapps.seotools.service.IWebsitesService;

@Controller
public class ImportKeywordsController {

    @Autowired
    private IWebsitesService sitesService;
    
    @RequestMapping(value="keyword-ranking/importKeywords", method=RequestMethod.GET)
    public @ResponseBody String importKeywords(@RequestParam("id") long siteID)
    {
        
        // Get the list of all existing categories
        List<Keyword> list = sitesService.findKeywords(siteID);
        
        String sReturn = Tools.convertKeywords(list);
        
        return sReturn;
    }
    
    public IWebsitesService getSitesService() {
        return sitesService;
    }

    public void setSitesService( IWebsitesService sitesService ) {
        this.sitesService = sitesService;
    }

}
