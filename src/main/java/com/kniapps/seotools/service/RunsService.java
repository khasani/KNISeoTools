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
    public List<Run> findRunsLastDays(long siteID, int iDays) {
        
        // Today date
        Date date_today = new Date();
        
        // Today -365 days
        Calendar cal = Calendar.getInstance();
        cal.setTime(date_today);
        cal.add(Calendar.DATE, -iDays);
        
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
    public Run launchRun(long siteID) {
        
        // Loading site
        Site site = siteDao.find(siteID);
        
        // Creating a new Run
        Run run = new Run();
        run.setDate(new Date());
        
        // Get site PR
        int PR = GoogleSeoHelper.getPR(site.getUrl());
        run.setPr(PR);
        
        // Get Indexed pages
        int indexedPages = GoogleSeoHelper.getIndexedPages(site.getUrl());
        run.setIndexedPages(indexedPages);
        
        // Searching Keywords Position
        String sDomain = Tools.getDomainName(site.getUrl());
        List<Keyword> list_keywords = keywordDao.findKeywords(siteID);
        for (Keyword keyword_temp : list_keywords) {
            
            // Get the Position
            StringBuilder sbOutput = new StringBuilder();
            int iPos = GoogleSeoHelper.getPostion(sDomain, keyword_temp.getName(), site.getSearchEngine().getUrl(), sbOutput);
            
            // Create Position
            Position pos = new Position();
            pos.setPos(iPos);
            keyword_temp.getPositions().add(pos);
            pos.setKeyword(keyword_temp);
            pos.setUrl(sbOutput.toString());
            
            // Add Position in the Run
            pos.setRun(run);
            run.getPositions().add(pos);
        }
              
        // Site Site to Run
        run.setSite(site);       
        site.getRuns().add(run);
        
        // Save the run in the database
        try {
            runDao.add(run);   
            
        } catch ( Exception e1 ) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        return run;
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
