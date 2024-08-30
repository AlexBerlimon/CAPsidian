package customer.capsidian.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.sap.cds.services.messages.Messages;

import cds.gen.notesservice.Notes;

@Component
@PropertySource("classpath:ValidationMessages.properties")
public class NotesValidator {
    
    @Value("${NO_DATA_MESSAGE}")
    private String NO_DATA_MESSAGE;

    @Value("${DATA_PATTERN_MESSAGE}")
    private String DATA_PATTERN_MESSAGE;

    @Value("${NAME_EXAMPLE}")
    private String NAME_EXAMPLE;
    
    @Autowired
    private Messages messages;

    public void validateNote(Notes note){
        validateTitle(note.getTitle());
        validateContent(note.getContent());
    }

    private void validateTitle(String title){
        if(title == null || title.isEmpty()){
            messages.error(NO_DATA_MESSAGE + Notes.TITLE);
        }else if (!title.matches("\\b[A-Z][a-z]+(?:-[A-Z][a-z]+)?\\b")){
            messages.error(DATA_PATTERN_MESSAGE + NAME_EXAMPLE);
        }
    }

    private void validateContent(String content){
        if(content == null || content.isEmpty()){
            messages.error(NO_DATA_MESSAGE + Notes.TITLE);
        }else if (!content.matches("\\b[A-Z][a-z]+(?:-[A-Z][a-z]+)?\\b")){
            messages.error(DATA_PATTERN_MESSAGE + NAME_EXAMPLE);
        }
    }
}
