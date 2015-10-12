package com.kniapps.seotools.dao;

import com.kniapps.seotools.model.Site;

public interface ISiteDao extends GenericDao<Site, Long> {

    public void remove(long siteId);
    
}
