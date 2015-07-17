package com.kniapps.seotools.keywordranking.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="kr_runs")
public class Run {

    private long id;
    private Date date;
    private int indexedPages;
    private int pr;
    private Site site;
    private Set<Position> positions = new HashSet<Position>(0);
    
    public Run() {
       
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

    @Temporal(value=TemporalType.TIMESTAMP)
    public Date getDate() {
        return date;
    }

    public void setDate( Date date ) {
        this.date = date;
    }

    @Column(name="indexed_pages")
    public int getIndexedPages() {
        return indexedPages;
    }

    public void setIndexedPages( int indexedPages ) {
        this.indexedPages = indexedPages;
    }

    @Column
    public int getPr() {
        return pr;
    }

    public void setPr( int pr ) {
        this.pr = pr;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kr_site_id", nullable = false)
    public Site getSite() {
        return site;
    }

    public void setSite( Site site ) {
        this.site = site;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "kr_positions")
    public Set<Position> getPositions() {
        return positions;
    }

    public void setPositions( Set<Position> positions ) {
        this.positions = positions;
    }
    
    

}
