package com.kniapps.seotools.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.kniapps.seotools.dao.INoteDao;
import com.kniapps.seotools.dao.IRunDao;
import com.kniapps.seotools.model.Note;

public class NotesService implements INotesService {

    @Autowired
    private INoteDao noteDao;
    
    @Transactional
    public List<Note> listNotes(long siteID ) {
        
        return noteDao.list(siteID);
    }
    
    @Transactional
    public void removeNote( long noteID ) {
        
        noteDao.remove(noteID);
    }

    public INoteDao getNoteDao() {
        return noteDao;
    }

    public void setNoteDao( INoteDao noteDao ) {
        this.noteDao = noteDao;
    }

    
    
}
