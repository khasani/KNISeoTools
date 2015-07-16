package com.kniapps.seotools.keywordranking.model;

import javax.persistence.Entity;
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

    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public int getPos() {
        return pos;
    }

    public void setPos( int pos ) {
        this.pos = pos;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl( String url ) {
        this.url = url;
    }

    public Run getRun() {
        return run;
    }

    public void setRun( Run run ) {
        this.run = run;
    }

    public Keyword getKeyword() {
        return keyword;
    }

    public void setKeyword( Keyword keyword ) {
        this.keyword = keyword;
    }
    
    

}
