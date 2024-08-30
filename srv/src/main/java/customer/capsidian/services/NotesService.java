package customer.capsidian.services;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.cds.services.EventContext;
import com.sap.cds.services.cds.CdsCreateEventContext;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.draft.DraftSaveEventContext;
import com.sap.cds.services.draft.DraftService;
import com.sap.cds.services.handler.annotations.After;
import com.sap.cds.services.handler.annotations.Before;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;

import cds.gen.notesservice.Notes;
import cds.gen.notesservice.NotesService_;
import cds.gen.notesservice.Notes_;
import customer.capsidian.dao.NotesDAO;
import customer.capsidian.handlers.CAPsidianEventHandler;
import customer.capsidian.handlers.NotesHandler;
import customer.capsidian.helpers.HandlersHelper;
import customer.capsidian.validators.NotesValidator;

@Component
@ServiceName(NotesService_.CDS_NAME)
public class NotesService extends CAPsidianEventHandler<Notes> {

    private NotesValidator validator;

    @Autowired
    private NotesDAO notesDAO;

    @Autowired
    private NotesHandler notesHandler;

    @Autowired
    private HandlersHelper handlersHelper;

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
    @On(event = CqnService.EVENT_CREATE, entity = Notes_.CDS_NAME)
    public void onAddNoteContext(CdsCreateEventContext context, Notes note) {
        notesHandler.process(context, note);
        context.setResult(Collections.singletonList(note));
        context.setCompleted();
    }

    @Before(event = { DraftService.EVENT_DRAFT_SAVE }, entity = Notes_.CDS_NAME)
    public void validate(DraftSaveEventContext context) {

        Map<String, Object> targetKeys = handlersHelper.extractTargetKeys(context);
        String NoteId = targetKeys.get("ID").toString();
        Boolean isActiveEntity = (Boolean) targetKeys.get("isActiveEntity");

        Optional<Notes> note = notesDAO.getNote(NoteId, isActiveEntity);
        if (note.isEmpty()) {
            return;
        }
        validator.validateNote(note.get());
    }
}
