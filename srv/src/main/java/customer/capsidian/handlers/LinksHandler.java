package customer.capsidian.handlers;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sap.cds.services.messages.Messages;

import cds.gen.linksservice.Links;
import cds.gen.linksservice.LinksService_;
import cds.gen.notesservice.Notes;
import customer.capsidian.dao.LinksDAO;


@Component
@Qualifier(LinksService_.CDS_NAME)
public class LinksHandler {
 
    @Autowired
    private Messages messages;
    
    @Autowired
    private LinksDAO linksDAO;

    public void extractLinks(Optional<Notes> noteEntity) {

        String content = noteEntity.get().getContent();

        // Внутренние ссылки [[Название заметки]] или [[Название заметки#Заголовок]]
        Pattern internalLinkPattern = Pattern.compile("\\[\\[([^\\]]+)]]");
        Matcher internalLinkMatcher = internalLinkPattern.matcher(content);

        while (internalLinkMatcher.find()) {
            addLink(internalLinkMatcher.group(), "Internal", noteEntity);
        }

        // Внешние ссылки [Текст ссылки](URL)
        Pattern externalLinkPattern = Pattern.compile("\\[([^\\]]+)\\]\\(([^)]+)\\)");
        Matcher externalLinkMatcher = externalLinkPattern.matcher(content);
        while (externalLinkMatcher.find()) {
            addLink(externalLinkMatcher.group(), "External", noteEntity);
        }

        // Файловые ссылки [[Название файла.ext]]
        Pattern fileLinkPattern = Pattern.compile("\\[\\[([^\\]]+\\.[^\\]]+)]]");
        Matcher fileLinkMatcher = fileLinkPattern.matcher(content);
        while (fileLinkMatcher.find()) {
            addLink(fileLinkMatcher.group(), "FileLink", noteEntity);
        }
        // Ссылки на медиафайлы ![[Название файла изображения.ext]]
        // или ![Альтернативный текст](Путь к файлу изображения)
        Pattern mediaLinkPattern = Pattern.compile("!\\[\\[([^\\]]+\\.[^\\]]+)]]|!\\[([^\\]]+)\\]\\(([^)]+)\\)");
        Matcher mediaLinkMatcher = mediaLinkPattern.matcher(content);
        while (mediaLinkMatcher.find()) {
            addLink(mediaLinkMatcher.group(), "MediaLink", noteEntity);
        }
        // Обратные ссылки (Backlinks) формируются динамически и не требуют анализа
        // контента

        // Ссылки на задачи и контрольные списки [ ] или [x]
        Pattern taskLinkPattern = Pattern.compile("\\[[ xX]\\]");
        Matcher taskLinkMatcher = taskLinkPattern.matcher(content);
        while (taskLinkMatcher.find()) {
            addLink(taskLinkMatcher.group(), "TaskLinkPattern", noteEntity);
        }

        // Кастомные ссылки (например, ==выделение== или ^Примечания)
        Pattern customLinkPattern = Pattern.compile("==([^=]+)==|\\^([^\\s]+)");
        Matcher customLinkMatcher = customLinkPattern.matcher(content);
        while (customLinkMatcher.find()) {
            addLink(customLinkMatcher.group(), "CustomLink", noteEntity);
        }

    }

    private void addLink(String linkTitle, String type, Optional<Notes> noteEntity) {
        
        Optional<Links> linkEntity = linksDAO.createNewLink();

        if (linkEntity.isPresent()) {
            linkEntity.get().setSourceNoteId(noteEntity.get().getId());
            linkEntity.get().setType("Internal");
            linkEntity.get().setTargetNoteId(linkTitle);
            linksDAO.save(linkEntity);
        } else {
            messages.error("Failed to create Link");
            messages.throwIfError();
        }
    }
}
