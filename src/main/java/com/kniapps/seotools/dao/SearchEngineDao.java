package com.kniapps.seotools.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.kniapps.seotools.model.Category;
import com.kniapps.seotools.model.SearchEngine;

@Transactional
public class SearchEngineDao implements ISearchEngineDao {

    @Autowired
    private SessionFactory sessionFactory;
    
    public SearchEngineDao() {
        // TODO Auto-generated constructor stub
    }

    public SearchEngine findSearchEngine( String sURL) {
        Query hqlQuery = getSessionFactory().getCurrentSession().createQuery("from SearchEngine where url = ?");
        List<SearchEngine> list = hqlQuery.setString(0,sURL).list();
        
        if (list.isEmpty()) return null;
        else return list.get(0);
    }

    public List<SearchEngine> listSearchEngines() {
        Query hqlQuery = getSessionFactory().getCurrentSession().createQuery("from SearchEngine");
        return hqlQuery.list();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory( SessionFactory sessionFactory ) {
        this.sessionFactory = sessionFactory;
    }
}
