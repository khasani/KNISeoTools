package com.kniapps.seotools.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="kr_notes")
public class Note {

    private long id;
    private Date date;
    private String description;
    private Site site;
    
    public Note() {
    }
    
    public Note( Date date, String description ) {
        this.date = date;
        this.description = description;
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

    @Temporal(TemporalType.TIMESTAMP)
    public Date getDate() {
        return date;
    }

    public void setDate( Date date ) {
        this.date = date;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    @Cascade({CascadeType.ALL})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kr_site_id", nullable = false)
    public Site getSite() {
        return site;
    }

    public void setSite( Site site ) {
        this.site = site;
    }
    
    

    
    
}
