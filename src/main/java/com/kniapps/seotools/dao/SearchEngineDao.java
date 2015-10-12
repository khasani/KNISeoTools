package com.kniapps.seotools.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.kniapps.seotools.model.Category;
import com.kniapps.seotools.model.Keyword;
import com.kniapps.seotools.model.SearchEngine;

@Transactional
public class SearchEngineDao extends HibernateDao<SearchEngine, Long> implements ISearchEngineDao {
    
    public SearchEngineDao() {
        // TODO Auto-generated constructor stub
    }

    public SearchEngine find(String sURL) {
        
        Criteria cr = currentSession().createCriteria(SearchEngine.class);
        cr.add(Restrictions.eq("url", sURL));
        List<SearchEngine> list = cr.list();
        
        if (list.isEmpty()) return null;
        else return list.get(0);
    }

}
