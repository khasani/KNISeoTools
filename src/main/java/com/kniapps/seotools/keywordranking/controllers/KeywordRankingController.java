package com.kniapps.seotools.keywordranking.controllers;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kniapps.seotools.keywordranking.model.Keyword;
import com.kniapps.seotools.keywordranking.model.Note;
import com.kniapps.seotools.keywordranking.model.Site;

@Controller
public class KeywordRankingController {
    
   
    @Autowired
    public SessionFactory sessionFactory;
    
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
        
        Session session = sessionFactory.openSession();
        
        session.beginTransaction();
        
        Site site1 = new Site("Gamewinner","www.gamewinner.fr",false);
        
        Note note1 = new Note(new Date(),"test");
        note1.setSite( site1 );
        session.save( note1 );
        
        Keyword keyword1 = new Keyword("keyword");
        keyword1.setSite( site1 );
        session.save( keyword1 );
        
        
        site1.getKeywords().add( keyword1 );
        site1.getNotes().add( note1 );
        session.save( site1 );
        
        session.getTransaction().commit();
        
        
        return "keyword-ranking/options";
    }
    
    

}
