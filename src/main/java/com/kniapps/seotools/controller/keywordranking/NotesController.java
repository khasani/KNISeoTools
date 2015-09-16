package com.kniapps.seotools.controller.keywordranking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kniapps.seotools.service.INotesService;

@Controller
public class NotesController {

    @Autowired
    private INotesService notesService;
    
    @RequestMapping(value="keyword-ranking/deleteNote", method=RequestMethod.POST)
    public @ResponseBody ResponseDelete deleteNote(@RequestParam("delete_note_id") long noteID)
    {
        ResponseDelete response = new ResponseDelete();
        
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
    
    
    public INotesService getNotesService() {
        return notesService;
    }

    public void setNotesService( INotesService notesService ) {
        this.notesService = notesService;
    }

}
