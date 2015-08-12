package com.kniapps.seotools.service;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kniapps.seotools.dao.ISiteDao;
import com.kniapps.seotools.dao.SiteDao;
import com.kniapps.seotools.model.Site;


public class SitesService implements ISitesService {

    @Autowired
    private ISiteDao siteDao;
    
    @Transactional(readOnly=true)
    public List<Site> searchSites() {
       
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return siteDao.searchSites(userName);
    }

    public ISiteDao getSiteDao() {
        return siteDao;
    }

    public void setSiteDao( ISiteDao siteDao ) {
        this.siteDao = siteDao;
    }

    

    
}
