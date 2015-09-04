package com.kniapps.seotools.dao;

import java.util.List;

import com.kniapps.seotools.model.Category;
import com.kniapps.seotools.model.Keyword;

public interface IKeywordDao {

    public List<Keyword> findKeywords(long siteID);
}
