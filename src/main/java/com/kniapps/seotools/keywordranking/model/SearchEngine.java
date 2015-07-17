package com.kniapps.seotools.keywordranking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="k_search_engines")
public class SearchEngine {

    private long id;
    private String url;
    private Site site;
    
    public SearchEngine() {
        
    }

    @Id
    @GeneratedValue
    @Column(name="id")
    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    @Column
    public String getUrl() {
        return url;
    }

    public void setUrl( String url ) {
        this.url = url;
    }

    @OneToOne(mappedBy = "kr_sites")
    public Site getSite() {
        return site;
    }

    public void setSite( Site site ) {
        this.site = site;
    }
    
    

}
