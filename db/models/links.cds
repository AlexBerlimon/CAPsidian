namespace db.capsidian;

using {
    cuid,
    managed
} from '@sap/cds/common';

using {db.capsidian.Notes} from './notes';

entity Links : cuid, managed {
    
    sourceNote : Association to one Notes;
    targetNote : Association to one Notes;
    linkType   : String(50);
    
}
