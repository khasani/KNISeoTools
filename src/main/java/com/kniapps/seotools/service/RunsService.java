package com.kniapps.seotools.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.kniapps.seotools.dao.IKeywordDao;
import com.kniapps.seotools.dao.IRunDao;
import com.kniapps.seotools.dao.ISiteDao;
import com.kniapps.seotools.model.Keyword;
import com.kniapps.seotools.model.Position;
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
        
        List<Keyword> list_keywords = keywordDao.findKeywords( siteID );
        
        Position pos1 = new Position();
        pos1.setPos(10);
        //list_keywords.get( 0 ).setPosition( pos1 );
        list_keywords.get( 0 ).getPositions().add( pos1 );
        pos1.setKeyword( list_keywords.get( 0 ) );
        pos1.setUrl("http://test.com");
        pos1.setRun( run );
        
        Position pos2 = new Position();
        pos2.setPos(15);
        //list_keywords.get( 1 ).setPosition( pos2 );
        list_keywords.get( 1 ).getPositions().add( pos2 );
        pos2.setKeyword( list_keywords.get( 1 ) );
        pos2.setUrl("http://test.com/test2");
        pos2.setRun( run );
        
        run.getPositions().add(pos1);
        run.getPositions().add(pos2);
        
        run.setSite(site);       
        
        // Adding a run
        site.getRuns().add(run);
        runDao.add(run);
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
