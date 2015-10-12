package com.kniapps.seotools.service;


import java.util.List;

import com.kniapps.seotools.model.Run;


public interface IRunsService {

    public List<Run> findRunsLastDays(long siteID, int iDays);
    public Run findLastRun(long siteID);
    public Run launchRun(long siteID);
}
