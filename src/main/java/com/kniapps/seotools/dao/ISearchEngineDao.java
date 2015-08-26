package com.kniapps.seotools.dao;

import java.util.List;

import com.kniapps.seotools.model.Category;
import com.kniapps.seotools.model.SearchEngine;

public interface ISearchEngineDao {

    public SearchEngine findSearchEngine(String sURL);
    public List<SearchEngine> listSearchEngines();
}
