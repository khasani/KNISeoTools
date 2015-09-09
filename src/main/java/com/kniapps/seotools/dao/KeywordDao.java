package com.kniapps.seotools.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.kniapps.seotools.model.Category;
import com.kniapps.seotools.model.Keyword;


public class KeywordDao extends HibernateDao<Keyword, Long> implements IKeywordDao {
   
    public List<Keyword> findKeywords(long siteID) {
        
        Criteria cr = currentSession().createCriteria(Keyword.class);
        cr.add(Restrictions.eq("site.id", siteID));
        List<Keyword> list = cr.list();
        
        if (list.isEmpty()) return null;
        else return list;
    }    
    
}
