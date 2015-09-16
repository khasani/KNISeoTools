package com.kniapps.seotools.dao;

import java.util.List;

import com.kniapps.seotools.model.Note;

public interface INoteDao extends GenericDao<Note, Long>{
    
    public List<Note> list(long siteID); 
    public void remove(long noteID);
    
}
