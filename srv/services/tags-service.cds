using {db.capsidian as db} from '../../db';
using from '../auth/links-service-auth';

@path: 'tags'
service TagsService {

    entity Notes as projection on db.Notes;
    entity Tags  as projection on db.Tags;
}
