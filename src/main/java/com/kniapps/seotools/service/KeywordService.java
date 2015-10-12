package com.kniapps.seotools.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.kniapps.seotools.dao.IKeywordDao;
import com.kniapps.seotools.model.Keyword;

public class KeywordService implements IKeywordService {

    @Autowired
    private IKeywordDao keywordDao;
    
    @Transactional(readOnly=true)
    public String findKeywordsAsString(long siteID) {
       
        List<Keyword> list = keywordDao.findKeywords(siteID);
        
        return Tools.convertKeywords(list);
    }

    public IKeywordDao getKeywordDao() {
        return keywordDao;
    }

    public void setKeywordDao( IKeywordDao keywordDao ) {
        this.keywordDao = keywordDao;
    }
    
    

}
