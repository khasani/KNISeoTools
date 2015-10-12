package com.kniapps.seotools.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.kniapps.seotools.dao.INoteDao;
import com.kniapps.seotools.dao.ISiteDao;
import com.kniapps.seotools.model.Note;
import com.kniapps.seotools.model.Site;

public class NotesService implements INotesService {

    @Autowired
    private INoteDao noteDao;
    
    @Autowired
    private ISiteDao siteDao;
    
    @Transactional
    public List<Note> listNotes(long siteID ) {
        
        return noteDao.list(siteID);
    }
    
    @Transactional
    public void removeNote( long noteID ) {
        
        noteDao.remove(noteID);
    }
    
    @Transactional
    public void addNote( Note note, long siteID ) {
        
        // Link note with site
        Site site = siteDao.find(siteID);
        note.setSite( site );
        
        // Add note
        noteDao.add(note);
    }

    public INoteDao getNoteDao() {
        return noteDao;
    }

    public void setNoteDao( INoteDao noteDao ) {
        this.noteDao = noteDao;
    }

    public ISiteDao getSiteDao() {
        return siteDao;
    }

    public void setSiteDao( ISiteDao siteDao ) {
        this.siteDao = siteDao;
    }
    
    

    
    
}
