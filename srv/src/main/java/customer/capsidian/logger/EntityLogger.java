package customer.capsidian.logger;

import com.sap.cds.services.EventContext;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class that log events that happen to an entity.
 * @param <Entity> the entity
 */
public class EntityLogger<Entity> {

    /**
     * Logger for events that happen to an entity.
     */
    private static final Logger LOGGER = Logger.getLogger(
        EntityLogger.class.getName()
    );

    /**
     * Log info message to console.
     * @param context  the context
     */
    public void info(final EventContext context) {
        this.log(Level.INFO, context);
    }

    /**
     * Log info message to console.
     * @param context  the context
     * @param entities  List of entities
     */
    public void info(final EventContext context, final List<Entity> entities) {
        this.log(Level.INFO, context, entities);
    }

    /**
     * Log warning message to console.
     * @param context the context
     */
    public void warning(final EventContext context) {
        this.log(Level.WARNING, context);
    }

    /**
     * Log warning message to console.
     * @param context
     * @param entities
     */
    public void warning(
        final EventContext context,
        final List<Entity> entities
    ) {
        this.log(Level.WARNING, context, entities);
    }

    /**
     * Log error message to console.
     * @param context the context
     */
    public void error(final EventContext context) {
        this.log(Level.SEVERE, context);
    }

    /**
     * Log error message to console.
     * @param context the context
     * @param entities  List of entities
     */
    public void error(final EventContext context, final List<Entity> entities) {
        this.log(Level.SEVERE, context, entities);
    }

    private void log(final Level level, final EventContext context) {
        LOGGER.log(level, this.buildLogRecord(context));
    }

    private String buildLogRecord(final EventContext context) {
        String tenant = context.getUserInfo().getTenant();
        if (tenant == null) {
            tenant = "local";
        }
        return (
            "Tenant: "
            + tenant
            + " | User: "
            + context.getUserInfo().getName()
            + " | Action: "
            + context.getEvent()
            + " | Service: "
            + context.getService().getName()
            + " | Entity: "
            + context.getTarget().getName()
        );
    }

    private void log(
        final Level level,
        final EventContext context,
        final List<Entity> entities
    ) {
        entities.forEach(entity -> {
            LOGGER.log(level, this.buildLogRecord(context, entity));
        });
    }

    private String buildLogRecord(
        final EventContext context,
        final Entity entity
    ) {
        return this.buildLogRecord(context) + " | Value: " + entity.toString();
    }
}
