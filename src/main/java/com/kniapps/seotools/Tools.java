package com.kniapps.seotools;

import java.util.HashSet;
import java.util.List;

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

}
