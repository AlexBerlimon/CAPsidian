package customer.capsidian.dao;

import static cds.gen.notesservice.NotesService_.NOTES;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sap.cds.ql.Insert;
import com.sap.cds.ql.Update;
import com.sap.cds.ql.cqn.CqnInsert;
import com.sap.cds.ql.cqn.CqnUpdate;
import com.sap.cds.services.persistence.PersistenceService;

import cds.gen.notesservice.AddedToNotes;
import cds.gen.notesservice.Notes;
import cds.gen.notesservice.NotesService_;
import cds.gen.linksservice.Links;
import cds.gen.linksservice.LinksService_;
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

    public void pushToNotes(AddedToNotes event) {

        Optional<Notes> noteEntity = createNote();
        noteEntity.get().setContent(event.getTitle());
        noteEntity.get().setTitle(event.getContent());
        Optional<Notes> noteFin = extractLinks(noteEntity);
        update(noteEntity);
    }

    private void update(Optional<Notes> noteEntity) {
        CqnUpdate update = Update.entity(NOTES).data(noteEntity.get());
        this.service.run(update);
    }

    private Optional<Notes> createNote() {
        Notes newNote = Notes.create();
        CqnInsert note = Insert.into(NOTES).entry(newNote);
        return this.service.run(note).first(Notes.class);
    }

    private Optional<Notes> extractLinks(Optional<Notes> noteEntity) {
        String content = noteEntity.get().getContent();
        // Внутренние ссылки [[Название заметки]] или [[Название заметки#Заголовок]]
        Pattern internalLinkPattern = Pattern.compile("\\[\\[([^\\]]+)]]");
        Matcher internalLinkMatcher = internalLinkPattern.matcher(content);
        while (internalLinkMatcher.find()) {
            Optional<Links> linkEntity = linksDAO.createNewLink();
            linkEntity.get().setSourceNoteId(noteEntity.get().getId());
            linkEntity.get().setType("Internal");
            String TargetID = internalLinkMatcher.group();
            linkEntity.get().setTargetNoteId(TargetID);
            linksDAO.save(linkEntity);
        }

        // // Внешние ссылки [Текст ссылки](URL)
        // Pattern externalLinkPattern =
        // Pattern.compile("\\[([^\\]]+)\\]\\(([^)]+)\\)");
        // Matcher externalLinkMatcher = externalLinkPattern.matcher(content);
        // while (externalLinkMatcher.find()) {
        // linksDAO.addLink(internalLinkMatcher.group(), 2);
        // }

        // // Файловые ссылки [[Название файла.ext]]
        // Pattern fileLinkPattern = Pattern.compile("\\[\\[([^\\]]+\\.[^\\]]+)]]");
        // Matcher fileLinkMatcher = fileLinkPattern.matcher(content);
        // while (fileLinkMatcher.find()) {
        // linksDAO.addLink(internalLinkMatcher.group(), 3);
        // }
        // // Ссылки на медиафайлы ![[Название файла изображения.ext]] или
        // ![Альтернативный
        // // текст](Путь к файлу изображения)
        // Pattern mediaLinkPattern =
        // Pattern.compile("!\\[\\[([^\\]]+\\.[^\\]]+)]]|!\\[([^\\]]+)\\]\\(([^)]+)\\)");
        // Matcher mediaLinkMatcher = mediaLinkPattern.matcher(content);
        // while (mediaLinkMatcher.find()) {
        // linksDAO.addLink(internalLinkMatcher.group(), 4);
        // }
        // // Обратные ссылки (Backlinks) формируются динамически и не требуют анализа
        // // контента

        // // Ссылки на задачи и контрольные списки [ ] или [x]
        // Pattern taskLinkPattern = Pattern.compile("\\[[ xX]\\]");
        // Matcher taskLinkMatcher = taskLinkPattern.matcher(content);
        // while (taskLinkMatcher.find()) {
        // linksDAO.addLink(internalLinkMatcher.group(), 5);
        // }

        // // Кастомные ссылки (например, ==выделение== или ^Примечания)
        // Pattern customLinkPattern = Pattern.compile("==([^=]+)==|\\^([^\\s]+)");
        // Matcher customLinkMatcher = customLinkPattern.matcher(content);
        // while (customLinkMatcher.find()) {
        // linksDAO.addLink(internalLinkMatcher.group(), 6);
        // }
         return noteEntity;
        // }

    }
}
