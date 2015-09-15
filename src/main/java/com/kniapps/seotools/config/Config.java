package com.kniapps.seotools.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kniapps.seotools.dao.CategoryDao;
import com.kniapps.seotools.dao.ICategoryDao;
import com.kniapps.seotools.dao.IKeywordDao;
import com.kniapps.seotools.dao.INoteDao;
import com.kniapps.seotools.dao.IRunDao;
import com.kniapps.seotools.dao.ISearchEngineDao;
import com.kniapps.seotools.dao.ISiteDao;
import com.kniapps.seotools.dao.KeywordDao;
import com.kniapps.seotools.dao.NoteDao;
import com.kniapps.seotools.dao.RunDao;
import com.kniapps.seotools.dao.SearchEngineDao;
import com.kniapps.seotools.dao.SiteDao;
import com.kniapps.seotools.service.INotesService;
import com.kniapps.seotools.service.IRunsService;
import com.kniapps.seotools.service.IWebsitesService;
import com.kniapps.seotools.service.NotesService;
import com.kniapps.seotools.service.RunsService;
import com.kniapps.seotools.service.WebsitesService;

@Configuration
public class Config {

    public Config() {
        // TODO Auto-generated constructor stub
    }
    
    /************ SERVICES *****************/
    
    @Bean
    public IWebsitesService sitesService(){
       return new WebsitesService();
    }
    
    @Bean
    public IRunsService runsService(){
       return new RunsService();
    }
    
    @Bean
    public INotesService notesService(){
       return new NotesService();
    }   
    
    /************ DAO **********************/
    
    @Bean
    public ISiteDao siteDao(){
       return new SiteDao();
    }
    
    @Bean
    public ICategoryDao categoryDao(){
       return new CategoryDao();
    }
    
    @Bean
    public ISearchEngineDao searchEngineDao(){
       return new SearchEngineDao();
    }
    
    @Bean
    public IKeywordDao keywordDao(){
       return new KeywordDao();
    }
    
    @Bean
    public IRunDao runDao(){
       return new RunDao();
    }
    
    @Bean
    public INoteDao noteDao(){
       return new NoteDao();
    }


}
