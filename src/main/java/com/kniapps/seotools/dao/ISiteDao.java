package com.kniapps.seotools.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.kniapps.seotools.model.Site;

public interface ISiteDao {

    public List<Site> listSites();
    public void addSite(Site site) throws Exception;
    public void removeSite(long siteId);
    public Site findSite(long id);
    
}
