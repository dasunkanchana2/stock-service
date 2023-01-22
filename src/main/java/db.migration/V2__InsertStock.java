package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The V2__InsertStock class for insert stock for stock table
 */
public class V2__InsertStock extends BaseJavaMigration {

    /**
     * Migration script
     *
     * @param context : context
     * @throws Exception : Exception
     */
    @Override
    public void migrate(Context context) throws Exception {
        insertStock(context);
    }

    /**
     * Insert stock
     *
     * @param context
     * @throws SQLException
     */
    public void insertStock(Context context) throws SQLException {
        try (PreparedStatement statement =
                     context
                             .getConnection()
                             .prepareStatement("INSERT INTO stock (id,currentPrice,isDeleted,lastUpdate,name) VALUES " +
                                     "(1,2.50,0,\"2023-01-22 06:35:39.771000\",\"Apple USA\"),\n"+
                                     "(2,1.50,0,\"2023-01-22 07:35:39.771000\",\"Stawberry NED\"),\n"+
                                     "(3,4.50,0,\"2023-01-22 08:35:39.771000\",\"Banana GER\"),\n"+
                                     "(4,5.50,0,\"2023-01-22 09:35:39.771000\",\"Grapes USA\"),\n"+
                                     "(5,6.50,0,\"2023-01-22 10:35:39.771000\",\"Avacado NZ\"),\n"+
                                     "(6,7.50,0,\"2023-01-22 16:35:39.771000\",\"Orange USA\")")) {
            statement.execute();
        }
    }
}
