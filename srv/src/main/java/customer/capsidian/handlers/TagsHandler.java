package customer.capsidian.handlers;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import cds.gen.notesservice.Notes;
import cds.gen.tagsservice.Tags;
import cds.gen.tagsservice.TagsService_;
import customer.capsidian.dao.TagsDAO;

@Component
@Qualifier(TagsService_.CDS_NAME)
public class TagsHandler {

    @Autowired
    private TagsDAO tagsDAO;

    public void extractTags(Optional<Notes> noteEntity) {

        String content = noteEntity.get().getContent();

        // Регулярное выражение для тегов, которые начинаются с символа #
        Pattern tagPattern = Pattern.compile("#\\w+");
        Matcher tagMatcher = tagPattern.matcher(content);

        while (tagMatcher.find()) {
            Optional<Tags> tagEntity = tagsDAO.createNewTag();
            if (tagEntity.isPresent()) {
                tagEntity.get().setSourceNoteId(noteEntity.get().getId());
                tagEntity.get().setTitle(tagMatcher.group());
            }
        }
    }

}
