package com.kniapps.seotools.service;

import java.util.List;

import com.kniapps.seotools.model.Note;

public interface INotesService {

    public List<Note> listNotes(long siteID);
    public void removeNote(long noteID);
    
}
