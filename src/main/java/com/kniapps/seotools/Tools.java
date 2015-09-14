package com.kniapps.seotools;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;


import java.util.Set;

import com.kniapps.seotools.model.Keyword;
import com.kniapps.seotools.model.Site;

public class Tools {

    // Tool Functions
    
    public static HashSet<Keyword> convertKeywords(String sKeywords, Site site)
    {
        HashSet<Keyword> list_keywords = new HashSet<Keyword>(0);
        
        String[] tab_keywords = sKeywords.split(",");
        for(int i=0 ; i<tab_keywords.length ; i++)
        {
            if (! tab_keywords[i].equals(""))
            {
                list_keywords.add( new Keyword(tab_keywords[i],site));
            }
        }
        return list_keywords;
    }
    
    public static String convertKeywords(List<Keyword> list_keywords)
    {
        String sReturn = "";
        
        if (list_keywords != null)
        {
            for(int i = 0 ; i<list_keywords.size(); i++)
            {
                sReturn += list_keywords.get(i).getName();
                
                if(i < list_keywords.size()-1) sReturn += ",";
            }
        }
        
        return sReturn;
    }
    
    public static String convertKeywords(Set<Keyword> list_keywords)
    {
        String sReturn = "";
        
        if (list_keywords != null)
        {
            
            int i=0;
            for (Iterator<Keyword> it = list_keywords.iterator(); it.hasNext(); ) 
            {         
                Keyword keyword = it.next();
                i++;
                
                sReturn += keyword.getName();
                
                if(i < list_keywords.size()) sReturn += ",";
            }
            
        }
        
        return sReturn;
    }
    
    public static boolean isKeywordInList(Keyword keyword, Set<Keyword> set_keywords)
    {
        String sKeywordName = keyword.getName();
        
        for (Iterator<Keyword> it = set_keywords.iterator(); it.hasNext(); ) 
        { 
            Keyword keyword_temp = it.next();
            
            if (sKeywordName.equals(keyword_temp.getName())) return true;
        }
        
        return false;
    }
    
}
