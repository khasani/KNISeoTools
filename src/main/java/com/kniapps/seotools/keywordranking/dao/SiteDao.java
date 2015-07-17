package com.kniapps.seotools.keywordranking.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import com.kniapps.seotools.keywordranking.model.Site;

//@Repository
public class SiteDao implements ISiteDao {

   // @PersistenceContext
    private EntityManager entityManager;
    
    
    public List<Site> searchSites( String username ) {
        // TODO Auto-generated method stub
        return null;
    }

    public void addSite( Site site ) {
        // TODO Auto-generated method stub
        
    }

    public void removeSite( long siteId ) {
        // TODO Auto-generated method stub
        
    }

}
