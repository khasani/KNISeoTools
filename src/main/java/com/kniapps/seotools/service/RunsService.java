package com.kniapps.seotools.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.kniapps.seotools.dao.IKeywordDao;
import com.kniapps.seotools.dao.IRunDao;
import com.kniapps.seotools.dao.ISiteDao;
import com.kniapps.seotools.model.Run;
import com.kniapps.seotools.model.Site;

public class RunsService implements IRunsService {

    @Autowired
    private ISiteDao siteDao;
    
    @Autowired
    private IRunDao runDao;
    
    @Autowired
    private IKeywordDao keywordDao;
        
    @Transactional
    public List<Run> findRunsLastMonth(long siteID) {

        // Today date
        Date date_today = new Date();
        
        // Today -30 days
        Calendar cal = Calendar.getInstance();
        cal.setTime(date_today);
        cal.add(Calendar.DATE, -30);
        
        // Get data from DAO
        List<Run> list = runDao.find(siteID, cal.getTime(), date_today);
        
        // Loading Keywords (LAZY)
        list.get(0).getPositions().size();
        
        if (list.isEmpty()) return null;
        else return list;
        
    }
    
    @Transactional
    public List<Run> findRunsLastYear(long siteID) {
        
        // Today date
        Date date_today = new Date();
        
        // Today -365 days
        Calendar cal = Calendar.getInstance();
        cal.setTime(date_today);
        cal.add(Calendar.DATE, -365);
        
        // Get data from DAO
        List<Run> list = runDao.find(siteID, cal.getTime(), date_today);
        
        
        if (list.isEmpty()) return null;
        else return list;
    }  

    @Transactional
    public Run findLastRun( long siteID ) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Transactional
    public void addRun( Run run, long siteID ) {
        
        // Loading
        Site site = siteDao.find(siteID);
        
        run.setSite(site);       
        
        // Adding a run
        //site.getRuns().add(run);
        
        runDao.add(run);
        
        // Saving the new run (updating site)
        //siteDao.update(site);
    }  
    
    /********* Getters / Setters  *******************************/

    public ISiteDao getSiteDao() {
        return siteDao;
    }

    public void setSiteDao( ISiteDao siteDao ) {
        this.siteDao = siteDao;
    }

    public IKeywordDao getKeywordDao() {
        return keywordDao;
    }

    public void setKeywordDao( IKeywordDao keywordDao ) {
        this.keywordDao = keywordDao;
    }

    public IRunDao getRunDao() {
        return runDao;
    }

    public void setRunDao( IRunDao runDao ) {
        this.runDao = runDao;
    }
}
