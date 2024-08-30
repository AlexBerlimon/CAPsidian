namespace db.capsidian;

using {
    cuid,
    managed
} from '@sap/cds/common';

using {db.capsidian.Notes} from './notes';

entity Tags : cuid, managed {
    sourceNote: Association to one Notes;
    title : String(50);
}