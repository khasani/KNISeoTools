package com.kniapps.seotools.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kniapps.seotools.dao.ISiteDao;
import com.kniapps.seotools.dao.SiteDao;
import com.kniapps.seotools.service.ISitesService;
import com.kniapps.seotools.service.SitesService;

@Configuration
public class Config {

    public Config() {
        // TODO Auto-generated constructor stub
    }
    
    @Bean
    public ISitesService sitesService(){
       return new SitesService();
    }
    
    @Bean
    public ISiteDao siteDAO(){
       return new SiteDao();
    }

}
