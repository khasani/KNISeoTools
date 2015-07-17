package com.kniapps.seotools.keywordranking.dao;

import java.util.List;

import com.kniapps.seotools.keywordranking.model.Site;

public interface ISiteDao {

    public List<Site> searchSites(String username);
    public void addSite(Site site);
    public void removeSite(long siteId);
    
}
