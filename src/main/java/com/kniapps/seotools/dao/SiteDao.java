package com.kniapps.seotools.dao;

import org.hibernate.Hibernate;
import org.springframework.transaction.annotation.Transactional;
import com.kniapps.seotools.model.Site;


@Transactional
public class SiteDao extends HibernateDao<Site, Long> implements ISiteDao {
   
    public void remove(long siteId) {
        
        // Load Site
        Site persistentInstance = (Site) currentSession().load(Site.class, siteId);
        
        // Loading Keywords (LAZY)
        Hibernate.initialize(persistentInstance.getKeywords());

        // Delete
        if (persistentInstance != null) {
            currentSession().delete(persistentInstance);
        }
    }
    
    @Override
    public Site find(Long id) {
        
        // Load Site
        Site persistentInstance = (Site) currentSession().load(Site.class, id);
        
        return persistentInstance;
    }
    

}
