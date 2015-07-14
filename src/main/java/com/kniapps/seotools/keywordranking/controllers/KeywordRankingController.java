package com.kniapps.seotools.keywordranking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KeywordRankingController {
    
    @RequestMapping("/keyword-ranking")
    public String showKeywordRankingDashboard(){
        
        
        return "keyword-ranking";
    }
    
    @RequestMapping("/keyword-ranking/websites")
    public String showKeywordRankingWebsites(){
        
        
        return "keyword-ranking/websites";
    }
    
    @RequestMapping("/keyword-ranking/keywords")
    public String showKeywordRankingKeywords(){
        
        
        return "keyword-ranking/keywords";
    }
    
    @RequestMapping("/keyword-ranking/options")
    public String showKeywordRankingOptions(){
        
        
        return "keyword-ranking/options";
    }
    
    

}
