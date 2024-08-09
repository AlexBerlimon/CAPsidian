namespace db.capsidian;

using {
    cuid,
    managed
} from '@sap/cds/common';

using db.capsidian.Notes from './notes';
using db.capsidian.Clients from './clients';

@cds.autoexpose
entity Collaborations : cuid, managed {

    note : Association to one Notes;
    clients : Association to one Clients;
    accessLevel : String(50);
    
}