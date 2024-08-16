using {db.capsidian as db} from '../db/models';

@path : 'notes'
service NotesService {
    entity Users as projection on db.Users;
    entity Notes as projection on db.Notes;
    entity Tags as projection on db.Tags;
    entity Links as projection on db.Links;

    action getLinkedNotes(noteID: UUID);
    }