package com.kniapps.seotools.controller.keywordranking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kniapps.seotools.service.IKeywordService;

@Controller
public class ImportKeywordsController {

    @Autowired
    private IKeywordService keywordsService;
    
    @RequestMapping(value="keyword-ranking/importKeywords", method=RequestMethod.GET)
    public @ResponseBody String importKeywords(@RequestParam("id") long siteID)
    {        
        return keywordsService.findKeywordsAsString(siteID);
    }

    public IKeywordService getKeywordsService() {
        return keywordsService;
    }

    public void setKeywordsService( IKeywordService keywordsService ) {
        this.keywordsService = keywordsService;
    }

}
