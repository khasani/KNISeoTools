package com.kniapps.seotools.controller.keywordranking;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KeywordsController {

  
    @RequestMapping("/keyword-ranking/keywords")
    public String showKeywordRankingKeywords(Model model){
        
        
        return "keyword-ranking/keywords";
    }

}
