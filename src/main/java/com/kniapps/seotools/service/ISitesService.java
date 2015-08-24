package com.kniapps.seotools.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kniapps.seotools.model.Category;
import com.kniapps.seotools.model.Site;

public interface ISitesService {

    List<Site> searchSites();
    Category searchCategory(String sCategory);
    List<Category> listCategories();
    void addCategory(Category category);
    void addSite(Site site);
}
