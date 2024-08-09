namespace db.capsidian;

using {
    cuid,
    managed
} from '@sap/cds/common';

using {db.capsidian.Tags} from './tags';


entity Notes : cuid, managed {

    title : String(100);
    content : LargeString;
    tags : Composition of many NoteTags on tags.Note = $self;

}

entity NoteTags : cuid, managed {
    title : String(100);
    content : LargeString;
    Note : Association to Notes;
    Tag : Association to Tags;
}