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
                         
        final Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        if(transaction.isActive()) transaction.rollback();

        try {
          transaction = session.beginTransaction();
          try {
              // The real work is here
              session.save(site);
              transaction.commit();
          } catch (Exception ex) {
            // Log the exception here
            transaction.rollback();
            throw ex;
          }
        } finally {
          
        }
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
