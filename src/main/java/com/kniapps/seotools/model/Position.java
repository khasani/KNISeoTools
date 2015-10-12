package com.kniapps.seotools.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;


@Entity
@Table(name="kr_positions")
public class Position {

    private long id;
    private int pos;
    private String url;
    private Run run;
    private Keyword keyword;
    
    public Position() {
        
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
    public int getPos() {
        return pos;
    }

    public void setPos( int pos ) {
        this.pos = pos;
    }

    @Column
    public String getUrl() {
        return url;
    }

    public void setUrl( String url ) {
        this.url = url;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kr_run_id", nullable = false)
    public Run getRun() {
        return run;
    }

    public void setRun( Run run ) {
        this.run = run;
    }

    //@Cascade({CascadeType.SAVE_UPDATE})
    //@OneToOne(mappedBy="position")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kr_keyword_id", nullable = false)
    public Keyword getKeyword() {
        return keyword;
    }

    public void setKeyword( Keyword keyword ) {
        this.keyword = keyword;
    }
    
    

}
