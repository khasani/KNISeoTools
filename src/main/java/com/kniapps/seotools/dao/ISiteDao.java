package com.kniapps.seotools.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.kniapps.seotools.model.Site;

public interface ISiteDao {

    public List<Site> searchSites(String username);
    public void addSite(Site site);
    public void removeSite(long siteId);
    
}
