package com.kniapps.seotools.service;


import java.util.List;

import com.kniapps.seotools.model.Run;


public interface IRunsService {

    public List<Run> findRunsLastYear(long siteID);
    public List<Run> findRunsLastMonth(long siteID);
    public Run findLastRun(long siteID);
    public void addRun(Run run,long siteID);
}
