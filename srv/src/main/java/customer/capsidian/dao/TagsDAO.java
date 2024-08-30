package customer.capsidian.dao;

import static cds.gen.tagsservice.TagsService_.*;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sap.cds.ql.Insert;
import com.sap.cds.ql.cqn.CqnInsert;
import com.sap.cds.services.persistence.PersistenceService;

import cds.gen.tagsservice.Tags;
import cds.gen.tagsservice.TagsService_;

@Component
@Qualifier(TagsService_.CDS_NAME)
public class TagsDAO {

    @Autowired
    private PersistenceService service;

    public Optional<Tags> createNewTag() {
        Tags tagEntity = Tags.create();
        CqnInsert insert = Insert.into(TAGS).entry(tagEntity);
        return service.run(insert).first(Tags.class);
    }
}
