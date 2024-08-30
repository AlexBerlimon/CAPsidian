package customer.capsidian.dao;

import static cds.gen.linksservice.LinksService_.*;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sap.cds.ql.Insert;
import com.sap.cds.ql.Update;
import com.sap.cds.ql.cqn.CqnInsert;
import com.sap.cds.ql.cqn.CqnUpdate;
import com.sap.cds.services.persistence.PersistenceService;

import cds.gen.linksservice.Links;
import cds.gen.linksservice.LinksService_;

@Component
@Qualifier(LinksService_.CDS_NAME)
public class LinksDAO {

    @Autowired
    private PersistenceService service;

    public Optional<Links> createNewLink() {
        Links linkEntity = Links.create();
        CqnInsert insert = Insert.into(LINKS).entry(linkEntity);
        return service.run(insert).first(Links.class);
    }

    public void save(Optional<Links> linkEntity) {
        CqnUpdate update = Update.entity(LINKS).entry(linkEntity.get());
        this.service.run(update);
    }
}
