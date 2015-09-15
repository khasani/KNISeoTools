package com.kniapps.seotools.dao;

import java.util.Date;
import java.util.List;

import com.kniapps.seotools.model.Run;

public interface IRunDao  extends GenericDao<Run, Long> {

    public List<Run> find(long siteID, Date date_start, Date date_end);
    
}
