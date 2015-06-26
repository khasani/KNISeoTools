package com.kniapps.kniseotools.keywordranking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/keyword-ranking")
public class KeywordRankingController {
    
    
    public String showKeywordRankingDashboard(){
        
        return "keyword-ranking";
    }

}
