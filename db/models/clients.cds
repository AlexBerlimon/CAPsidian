namespace db.capsidian;

using {
    cuid,
    managed
} from '@sap/cds/common';

entity Clients : cuid, managed {
    
    @mandatory
    @assert.format: '\b[A-Z][a-z]+(?:-[A-Z][a-z]+)?\b'
    @assert.notNull
    username : String(50);

    @mandatory
    @assert.format: '\b[A-Z][a-z]+(?:-[A-Z][a-z]+)?\b'
    @assert.notNull
    email    : String(50);

}
