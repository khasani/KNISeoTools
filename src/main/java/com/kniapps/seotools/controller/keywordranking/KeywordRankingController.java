package com.kniapps.seotools.controller.keywordranking;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class KeywordRankingController {
    
    @RequestMapping("/keyword-ranking")
    public String showKeywordRankingDashboard(Model model){
        
        
        
        return "keyword-ranking";
    }

}
