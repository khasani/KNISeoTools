package com.kniapps.seotools.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.kniapps.seotools.model.Note;

@Transactional
public class NoteDao extends HibernateDao<Note, Long> implements INoteDao {

    public List<Note> list( long siteID ) {
        
        Criteria cr = currentSession().createCriteria(Note.class);
        cr.add(Restrictions.eq("site.id", siteID));
        
        List<Note> list = cr.list();
        
        if (list.isEmpty()) return null;
        else return list;
    }

    public void remove(long noteID) {
        
        // Load Note
        Note note = (Note) currentSession().load(Note.class, noteID);
        
        // Delete
        if (note != null) {
            currentSession().delete(note);
        }
    }

}
