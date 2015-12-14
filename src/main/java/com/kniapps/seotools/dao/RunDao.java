package com.kniapps.seotools.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.kniapps.seotools.model.Note;
import com.kniapps.seotools.model.Run;

@Transactional
public class RunDao extends HibernateDao<Run, Long> implements IRunDao {

    public List<Run> find( long siteID, Date date_start, Date date_end ) {
       
        Criteria cr = currentSession().createCriteria(Run.class);
        //cr.setFetchMode("positions", FetchMode.JOIN);
        cr.add(Restrictions.eq("site.id", siteID));
        cr.add(Restrictions.ge("date",date_start));
        cr.add(Restrictions.lt("date",date_end));
        cr.addOrder( Order.desc("date"));
        
        List<Run> list = (List<Run>) cr.list();
        
        if (list.isEmpty()) return null;
        else return list;
    }
    
    public void remove(long runID) {
        
        // Load Note
        Run run = (Run) currentSession().load(Run.class, runID);
        
        // Delete
        if (run != null) {
            currentSession().delete(run);
        }
    }

}
