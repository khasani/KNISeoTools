package com.kniapps.seotools.dao;

import java.util.List;

import com.kniapps.seotools.model.Keyword;

public interface IKeywordDao extends GenericDao<Keyword, Long> {

    public List<Keyword> findKeywords(long siteID);
}
