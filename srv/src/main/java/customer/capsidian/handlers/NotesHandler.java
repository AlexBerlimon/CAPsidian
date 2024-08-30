package customer.capsidian.handlers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sap.cds.services.cds.CdsCreateEventContext;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.messages.Messages;

import cds.gen.linksservice.LinksService_;
import cds.gen.notesservice.Notes;
import cds.gen.notesservice.NotesService_;
import cds.gen.tagsservice.TagsService_;
import customer.capsidian.dao.LinksDAO;
import customer.capsidian.dao.NotesDAO;
import customer.capsidian.validators.NotesValidator;

@Component
@Qualifier(NotesService_.CDS_NAME)
public class NotesHandler {

    @Autowired
    private Messages messages;

    @Autowired
    @Qualifier(NotesService_.CDS_NAME)
    private CqnService notesService;

    @Autowired
    private NotesValidator notesValidator;

    @Autowired
    @Qualifier(LinksService_.CDS_NAME)
    private LinksHandler linksHandler;

    @Autowired
    @Qualifier(TagsService_.CDS_NAME)
    private TagsHandler tagsHandler;

    @Autowired
    private LinksDAO linksDAO;
    @Autowired
    private NotesDAO notesDAO;

    public void process(CdsCreateEventContext context, Notes note) {
        notesValidator.validateNote(note);
        Optional<Notes> noteEntity = notesDAO.pushToNotes(note);
        if (noteEntity.isPresent()) {
            linksHandler.extractLinks(noteEntity);
            tagsHandler.extractTags(noteEntity);
        } else {
            messages.error("Failed to create Link");
            messages.throwIfError();
        }
    }
}