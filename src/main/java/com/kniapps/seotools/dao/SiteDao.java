package com.kniapps.seotools.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kniapps.seotools.model.Category;
import com.kniapps.seotools.model.Site;
import com.kniapps.seotools.model.User;

@Transactional
public class SiteDao implements ISiteDao {
   
    @Autowired
    private SessionFactory sessionFactory;
    
    public List<Site> listSites() {
                       
        Query hqlQuery = getSessionFactory().getCurrentSession().createQuery("from Site");
        return hqlQuery.list();
    }

    public void addSite(Site site) throws Exception {
                         
        try {
        
            getSessionFactory().getCurrentSession().save(site);
              
          } catch (Exception ex) {
        
            throw ex;
          }
    }

    public void removeSite( long siteId ) {
        
        Session session = getSessionFactory().getCurrentSession();
        Object persistentInstance = session.load(Site.class, siteId);
        if (persistentInstance != null) {
            session.delete(persistentInstance);
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory( SessionFactory sessionFactory ) {
        this.sessionFactory = sessionFactory;
    }
    
    

}
