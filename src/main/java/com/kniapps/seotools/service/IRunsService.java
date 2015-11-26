package com.kniapps.seotools.service;


import java.util.ArrayList;
import java.util.List;

import com.kniapps.seotools.model.Position;
import com.kniapps.seotools.model.Run;


public interface IRunsService {

    public ArrayList<ArrayList<Object>> findRunsPositionsLastDays(long siteID, int iDays);
    public Run findLastRun(long siteID);
    public Run launchRun(long siteID);
}
