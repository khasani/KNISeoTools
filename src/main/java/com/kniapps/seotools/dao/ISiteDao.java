package com.kniapps.seotools.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.kniapps.seotools.model.Keyword;
import com.kniapps.seotools.model.Site;

public interface ISiteDao extends GenericDao<Site, Long> {

    public void remove(long siteId);
    //public Site findSite(long id);*/
    
}
