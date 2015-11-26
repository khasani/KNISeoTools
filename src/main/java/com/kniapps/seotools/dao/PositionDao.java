package com.kniapps.seotools.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.kniapps.seotools.model.Position;
import com.kniapps.seotools.model.Run;

public class PositionDao  extends HibernateDao<Position, Long> implements IPositionDao{

    public List<Position> find( long siteID, Date date_start, Date date_end ) {
        
        Criteria cr = currentSession().createCriteria(Position.class);
        cr.createAlias("run", "run");
        //cr.setFetchMode("run", FetchMode.JOIN);
        cr.add(Restrictions.eq("run.site.id", siteID));
        cr.add(Restrictions.ge("run.date",date_start));
        cr.add(Restrictions.lt("run.date",date_end));
        cr.addOrder(Order.desc("run.date"));
        
        List<Position> list = (List<Position>) cr.list();
        
        if (list.isEmpty()) return null;
        else return list;
    }

}
