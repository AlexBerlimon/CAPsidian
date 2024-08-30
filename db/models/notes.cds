namespace db.capsidian;

using {
    cuid,
    managed
} from '@sap/cds/common';

entity Notes : cuid, managed {

    title : String(100);
    content : LargeString;

}
