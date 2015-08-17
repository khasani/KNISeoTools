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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="kr_sites")
public class Site {
    
    private long id;
    private String name;
    private String url;
    private boolean runDaily;
    private Set<Note> notes = new HashSet<Note>(0);
    private Set<Keyword> keywords = new HashSet<Keyword>(0);
    private SearchEngine searchEngine;
    private Category category;
    private User user;
    
    public Site() {
    }
    
    public Site( String name, String url, boolean runDaily ) {
        this.name = name;
        this.url = url;
        this.runDaily = runDaily;
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

    @Column
    public String getUrl() {
        return url;
    }

    public void setUrl( String url ) {
        this.url = url;
    }

    @Column(name="run_daily")
    public boolean isRunDaily() {
        return runDaily;
    }

    public void setRunDaily( boolean runDaily ) {
        this.runDaily = runDaily;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes( Set<Note> notes ) {
        this.notes = notes;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
    public Set<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords( Set<Keyword> keywords ) {
        this.keywords = keywords;
    }

    @OneToOne
    @JoinColumn(name="kr_search_engine_id", referencedColumnName="id")
    public SearchEngine getSearchEngine() {
        return searchEngine;
    }

    public void setSearchEngine( SearchEngine searchEngine ) {
        this.searchEngine = searchEngine;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kr_user_username", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser( User user ) {
        this.user = user;
    }

    @OneToOne
    @JoinColumn(name="kr_category_id", referencedColumnName="id")
    public Category getCategory() {
        return category;
    }

    public void setCategory( Category category ) {
        this.category = category;
    }
    
    
    
}