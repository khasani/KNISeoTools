package com.kniapps.seotools.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Table(name="kr_keywords")
public class Keyword {
    
    private long id;
    private String name;
    private Site site;
    
    public Keyword() {
    }
    
    public Keyword( String name ) {
        
        this.name = name;
    }
    
    public Keyword( String name, Site site ) {
        
        this.name = name;
        this.site = site;
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
    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kr_site_id", nullable = false)
    public Site getSite() {
        return site;
    }

    public void setSite( Site site ) {
        this.site = site;
    }
    

}
