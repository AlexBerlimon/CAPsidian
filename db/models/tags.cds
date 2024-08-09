namespace db.capsidian;

using {
    cuid,
    managed
} from '@sap/cds/common';

entity Tags : cuid, managed {
   name : String(50);
}