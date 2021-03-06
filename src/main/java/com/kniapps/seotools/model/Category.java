package com.kniapps.seotools.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="kr_categories")
public class Category {

    private long id;
    private String name;
    private Site site;
    
    public Category() {
    }
    
    public Category(String name) {
        this.name = name;
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

    @OneToOne
    @PrimaryKeyJoinColumn
    public Site getSite() {
        return site;
    }

    public void setSite( Site site ) {
        this.site = site;
    }
    
    

    
    
    
    

}
