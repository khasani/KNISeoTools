package com.kniapps.seotools.dao;

import com.kniapps.seotools.model.SearchEngine;

public interface ISearchEngineDao extends GenericDao<SearchEngine, Long> {

    public SearchEngine find(String sURL);
}
