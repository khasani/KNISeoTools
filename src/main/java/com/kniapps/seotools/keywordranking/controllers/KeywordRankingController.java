package com.kniapps.seotools.keywordranking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KeywordRankingController {
    
    @RequestMapping("/keyword-ranking")
    public String showKeywordRankingDashboard(){
        
        //return "keyword-ranking";
        return "home";
    }

}
