package com.kniapps.seotools.dao;

import java.util.List;

import com.kniapps.seotools.model.Category;
import com.kniapps.seotools.model.Keyword;
import com.kniapps.seotools.model.SearchEngine;

public interface ISearchEngineDao extends GenericDao<SearchEngine, Long> {

    public SearchEngine find(String sURL);
}
