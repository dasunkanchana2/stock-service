package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The V1__CreateStock class for create stock table in the stock db
 */
public class V1__CreateStock extends BaseJavaMigration {

    /**
     * Migration script
     *
     * @param context : context
     * @throws Exception : Exception
     */
    @Override
    public void migrate(Context context) throws Exception {
        stockCreate(context);
    }

    /**
     *
     * stock table creation script
     *
     * @param context
     * @throws SQLException
     */
    public void stockCreate(Context context) throws SQLException {
        try (PreparedStatement statement =
                     context
                             .getConnection()
                             .prepareStatement("CREATE TABLE IF NOT EXISTS `stock` (\n" +
                                     "  `id` int NOT NULL AUTO_INCREMENT,\n" +
                                     "  `currentPrice` decimal(19,2) NOT NULL,\n" +
                                     "  `isDeleted` int NOT NULL,\n" +
                                     "  `lastUpdate` datetime(6) DEFAULT NULL,\n" +
                                     "  `name` varchar(255) NOT NULL,\n" +
                                     "  PRIMARY KEY (`id`)\n" +
                                     ")")) {
            statement.execute();
        }
    }
}
