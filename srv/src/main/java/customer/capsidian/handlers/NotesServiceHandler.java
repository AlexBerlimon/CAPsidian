package customer.capsidian.handlers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sap.cds.services.EventContext;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.handler.annotations.After;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;

import cds.gen.notesservice.AddNoteContext;
import cds.gen.notesservice.AddedToNotes;
import cds.gen.notesservice.AddedToNotesContext;
import cds.gen.notesservice.Notes;
import cds.gen.notesservice.NotesService_;
import customer.capsidian.dao.NotesDAO;

@Component
@ServiceName(NotesService_.CDS_NAME)
public class NotesServiceHandler extends CAPsidianEventHandler<Notes> {
    @Autowired
    @Qualifier(NotesService_.CDS_NAME)
    private CqnService notesService;
    
    @Autowired
    private NotesDAO notesDAO;

    /**
     * Log READ, CREATE, UPDATE, DELETE events of Items.
     *
     * @param context the context
     * @param items   List of items
     */
    @After(event = { CqnService.EVENT_READ, CqnService.EVENT_CREATE, CqnService.EVENT_UPDATE,
            CqnService.EVENT_DELETE, })
    public void afterAny(final EventContext context, final List<Notes> notes) {
        if (notes == null || notes.isEmpty()) {
            this.getLogger().info(context);
        } else {
            this.getLogger().info(context, notes);
        }
    }

    /**
     * Handle added notes event.
     * 
     * @param context Contains data from the added notes event
     * 
     */
    @On(event = AddNoteContext.CDS_NAME)
    public void onAddNoteContext(final AddNoteContext context) {
        AddedToNotes event = this.getAddedNoteEvents(context);
        //this.addingToNotesValidator.validate(event);
        this.notesDAO.pushToNotes(event);
        this.emitAdded(event);
        context.setCompleted();
    }

    private void emitAdded(AddedToNotes event) {
        AddedToNotesContext addedToNotesContext = AddedToNotesContext.create();
        addedToNotesContext.setData(event);
        this.notesService.emit(addedToNotesContext);
    }

    private AddedToNotes getAddedNoteEvents(AddNoteContext context) {
        AddedToNotes result = AddedToNotes.create();
        result.setContent(context.getNoteID());
        //context.getUserInfo().getId();
        //Optional<Notes> noteEntity = notesDAO.getNoteByID(context.getNote());
        return result;
    }

}
