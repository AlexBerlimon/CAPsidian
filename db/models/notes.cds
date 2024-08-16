namespace db.capsidian;

using {
    cuid,
    managed
} from '@sap/cds/common';

using {db.capsidian.Tags} from './tags';


entity Notes : cuid, managed {

    title : String(100);
    content : LargeString;

}
