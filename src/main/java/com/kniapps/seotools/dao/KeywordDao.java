package com.kniapps.seotools.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kniapps.seotools.model.Category;
import com.kniapps.seotools.model.Keyword;


public class KeywordDao implements IKeywordDao {

    @Autowired
    private SessionFactory sessionFactory;
    
    public List<Keyword> findKeywords( long siteID ) {
        Query hqlQuery = getSessionFactory().getCurrentSession().createQuery("from Keyword where site.id=?");
        
        List<Keyword> list = hqlQuery.setLong(0,siteID).list();
        
        if (list.isEmpty()) return null;
        else return list;
    }    
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory( SessionFactory sessionFactory ) {
        this.sessionFactory = sessionFactory;
    }


}
