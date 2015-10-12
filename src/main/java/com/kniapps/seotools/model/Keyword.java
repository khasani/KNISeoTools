package com.kniapps.seotools.model;

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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Table(name="kr_keywords")
public class Keyword {
    
    private long id;
    private String name;
    private Site site;
    private Set<Position> positions = new HashSet<Position>(0);
    
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kr_site_id", referencedColumnName = "id", nullable = false)
    public Site getSite() {
        return site;
    }

    public void setSite( Site site ) {
        this.site = site;
    }


    /*public Position getPosition() {
        return position;
    }

    public void setPosition( Position position ) {
        this.position = position;
    }*/

    @Cascade({CascadeType.SAVE_UPDATE})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "keyword", orphanRemoval=true)
    public Set<Position> getPositions() {
        return positions;
    }

    public void setPositions( Set<Position> positions ) {
        this.positions = positions;
    }

}
