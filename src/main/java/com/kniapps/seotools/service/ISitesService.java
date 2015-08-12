package com.kniapps.seotools.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kniapps.seotools.model.Site;

public interface ISitesService {

    List<Site> searchSites();
}
