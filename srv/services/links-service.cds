using {db.capsidian as db} from '../../db';
using from '../auth/links-service-auth';

@path: 'links'
service LinksService {
    entity Notes as projection on db.Notes;
    entity Tags  as projection on db.Tags;
    entity Links as projection on db.Links order by sourceNote;

}
