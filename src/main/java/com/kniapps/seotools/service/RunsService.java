package com.kniapps.seotools.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.kniapps.seotools.dao.IKeywordDao;
import com.kniapps.seotools.dao.IPositionDao;
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
    private IPositionDao positionDao;
    
    @Autowired
    private IKeywordDao keywordDao;
           
    @Transactional
    public ArrayList<ArrayList<Object>> findRunsPositionsLastDays(long siteID, int iDays) {
        
        ArrayList<ArrayList<Object>> arrayReturn = new ArrayList<ArrayList<Object>>();
        
        Date date_today = new Date();
        
        // Today -365 days
        Calendar cal = Calendar.getInstance();
        cal.setTime(date_today);
        cal.add(Calendar.DATE, -iDays);
        
        // Get data from DAO
        List<Run> list_run = runDao.find(siteID, cal.getTime(), date_today);
        List<Keyword> list_keywords = keywordDao.findKeywords(siteID);
        
        // Convert data in array to be easily showed in front-end
        arrayReturn = convertRunsInArray(list_run,list_keywords);
        
       return arrayReturn;
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
    
    @Transactional
    public void removeRun(long runID) {
        
        runDao.remove(runID);
    }
    
    /********* Format Return Data  *******************************/
    
    private ArrayList<ArrayList<Object>> convertRunsInArray(List<Run> list_run, List<Keyword> list_keywords)
    {
        ArrayList<ArrayList<Object>> arrayReturn = new ArrayList<ArrayList<Object>>();
        
        // First row : Run ID
        ArrayList<Object> array_run_id = new ArrayList<Object>();
        array_run_id.add("ID");
        for (Run run : list_run) 
        {
            array_run_id.add(run.getId());           
        }
        arrayReturn.add(array_run_id);
        
        // Second row : Date of the run
        ArrayList<Object> array_date = new ArrayList<Object>();
        array_date.add("Date");
        for (Run run : list_run) 
        {
            array_date.add(run.getDate());           
        }
        arrayReturn.add(array_date);
        
        // Third row : PR
        ArrayList<Object> array_pr = new ArrayList<Object>();
        array_pr.add("PR");
        for (Run run : list_run) 
        {
            array_pr.add(run.getPr());            
        }
        arrayReturn.add(array_pr);
        
        // 4th row : Indexed Pages
        ArrayList<Object> array_indexed = new ArrayList<Object>();
        array_indexed.add("Indexed Pages");
        for (Run run : list_run) 
        {
            array_indexed.add(run.getIndexedPages());            
        }
        arrayReturn.add(array_indexed);
        
        // Next rows : Keywords Position
        for (Keyword keyword : list_keywords) 
        {
            // Init the Keyword array with as first parameter the Keyword name
            ArrayList<Object> array_keywords = new ArrayList<Object>();
            array_keywords.add(keyword.getName());
            
            for (Run run : list_run) 
            {
                Set<Position> set_pos = run.getPositions();
                Position pos_temp = null;
                for (Position pos : set_pos) 
                {
                    if(pos.getKeyword().getName().equals(keyword.getName()))
                    {
                        pos_temp = pos;
                        break;
                    }
                
                }
                array_keywords.add(pos_temp);
            
            }
            
            arrayReturn.add(array_keywords);
            
        }
        
        //System.out.println(arrayReturn);
 
        return arrayReturn; 
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

    public IPositionDao getPositionDao() {
        return positionDao;
    }

    public void setPositionDao( IPositionDao positionDao ) {
        this.positionDao = positionDao;
    }
    
    
    
    
}
