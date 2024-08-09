using {db.capsidian as db} from '../../db/models';

@path : 'notes'
service NotesService {
    entity Clients as projection on db.Clients;
    entity Notes as projection on db.Notes;
    entity Tags as projection on db.Tags;
    entity Links as projection on db.Links;
    entity Collaborations as projection on db.Collaborations;

    action getLinkedNotes(noteID: UUID);
    }