package com.kniapps.seotools.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kniapps.seotools.model.Site;
import com.kniapps.seotools.model.User;

public class SiteDao implements ISiteDao {
   
    @Autowired
    private SessionFactory sessionFactory;
    
    public List<Site> searchSites(String username) {
                       
        Query hqlQuery = getSessionFactory().getCurrentSession().createQuery("from Site");
        return hqlQuery.list();
    }

    public void addSite(Site site) {
                     
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(site);
        session.getTransaction().commit();
    }

    public void removeSite( long siteId ) {
        // TODO Auto-generated method stub
        
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory( SessionFactory sessionFactory ) {
        this.sessionFactory = sessionFactory;
    }
    
    

}
