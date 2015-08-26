package com.kniapps.seotools.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="kr_search_engines")
public class SearchEngine {

    private long id;
    private String url;
    
    public SearchEngine() {
        
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
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

    
    
    

}
