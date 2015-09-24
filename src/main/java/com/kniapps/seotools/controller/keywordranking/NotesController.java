package com.kniapps.seotools.controller.keywordranking;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kniapps.seotools.model.Note;
import com.kniapps.seotools.service.INotesService;

@Controller
public class NotesController {

    @Autowired
    private INotesService notesService;
    
    @RequestMapping(value="keyword-ranking/deleteNote", method=RequestMethod.POST)
    public @ResponseBody ResponseBoolean deleteNote(@RequestParam("delete_note_id") long noteID)
    {
        ResponseBoolean response = new ResponseBoolean();
        
        try {
            notesService.removeNote(noteID);
            response.success = true;
        } catch ( Exception e ) {
            // TODO Auto-generated catch block
            response.success = false;
            response.message = "Error when deleting selected note !";
            e.printStackTrace();
        }
        
        return response;
    }
    
    @RequestMapping(value="keyword-ranking/addNote", method=RequestMethod.POST)
    public String addNote(@RequestParam("text_add_note") String sNote,@RequestParam("site_id") long siteID)
    {
                
        try {
            
            Note note = new Note(new Date(),sNote);
            notesService.addNote(note,siteID);
            
        } catch ( Exception e ) {

            e.printStackTrace();
        }
        
        return "redirect:website?id=" + String.valueOf(siteID);
    }
    
    
    public INotesService getNotesService() {
        return notesService;
    }

    public void setNotesService( INotesService notesService ) {
        this.notesService = notesService;
    }

}
