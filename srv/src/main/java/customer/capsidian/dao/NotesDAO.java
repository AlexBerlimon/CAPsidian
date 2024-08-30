package customer.capsidian.dao;

import static cds.gen.notesservice.NotesService_.*;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sap.cds.ql.Insert;
import com.sap.cds.ql.Select;
import com.sap.cds.ql.cqn.CqnInsert;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.draft.DraftService;
import com.sap.cds.services.persistence.PersistenceService;

import cds.gen.notesservice.Notes;
import cds.gen.notesservice.NotesService_;

/**
 * DAO для работы с заметками.
 */

@Component
@Qualifier(NotesService_.CDS_NAME)
public class NotesDAO {

    @Autowired
    private LinksDAO linksDAO;

    @Autowired
    private PersistenceService service;

    @Autowired
    @Qualifier(NotesService_.CDS_NAME)
    private DraftService draftService;

    public Optional<Notes> pushToNotes(Notes note) {
        CqnInsert insert = Insert.into(NOTES).entry(note);
        return service.run(insert).first(Notes.class);
    }

    public Optional<Notes> getNoteByID(String noteID) {
        CqnSelect select = Select.from(NOTES).where(c -> c.ID().eq(noteID));
        return this.service.run(select).first(Notes.class);
    }

    public Optional<Notes> getNote(String noteId, Boolean isActiveEntity) {
        CqnSelect select = Select.from(NOTES)
                .where(o -> o.ID().eq(noteId).and(o.IsActiveEntity().eq(isActiveEntity)));
        return draftService.run(select).first(Notes.class);
    }
}
