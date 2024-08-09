using {db.capsidian as db} from '../../db/models';

@path: 'clients'
service ClientsService {

    entity Clients        as projection on db.Clients;
    entity Collaborations as projection on db.Collaborations;

}
