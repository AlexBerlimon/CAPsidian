using {db.capsidian as db} from '../../db';
using from '../auth/notes-service-auth';

@path: 'notes'
service NotesService {
    entity Notes as projection on db.Notes
                    order by
                        createdAt desc

    entity Tags  as projection on db.Tags;
    entity Links as projection on db.Links;

    action addNote(noteID : db.Notes:ID);

    event addedToNotes {
        noteID  : db.Notes:ID;
        tagID   : db.Tags:ID;
        linkID  : db.Links:ID;
        content : db.Notes:content;
        title   : db.Notes:title;
    }


}
