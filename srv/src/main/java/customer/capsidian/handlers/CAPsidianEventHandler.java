package customer.capsidian.handlers;

import java.util.List;

import com.sap.cds.services.EventContext;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;

import customer.capsidian.logger.EntityLogger;

public abstract class CAPsidianEventHandler<MainEntity>
        implements EventHandler {

    /**
     * Logger for main application entity.
     */
    private EntityLogger<MainEntity> logger = new EntityLogger<MainEntity>();

    /**
     * Getter for logger.
     * @return Logger
     */
    public EntityLogger<MainEntity> getLogger() {
        return logger;
    }

    /**
     * Setter for logger.
     * @param logger Logger
     */
    public void setLogger(final EntityLogger<MainEntity> logger) {
        this.logger = logger;
    }

    /**
     * READ, CREATE, UPDATE, DELETE events handler.
     * @param context  the context
     * @param entity  List of items
     */
    public abstract void afterAny(
        EventContext context,
        List<MainEntity> entity
    );

    // @On(event = AddNoteContext.CDS_NAME)
    // public void on AddNoteContext(final AddContext context){
    //     AddedContext event = 
    //     this.getAddedNoteEvent(final AddNoteContext context);
    // }
}
   