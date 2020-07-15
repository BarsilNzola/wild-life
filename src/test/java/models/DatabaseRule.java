package models;

import models.DB;
import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

    @Override
    protected void before() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "barsil", "madboysent7042");
    }

    @Override
    protected void after() {
        try(Connection con = DB.sql2o.open()) {
            String deleteAnimalsQuery = "DELETE FROM animals *;";
            String deleteSightingsQuery = "DELETE FROM sightings *;";
            String deletesRangersQuery = "DELETE FROM rangers *;";
            con.createQuery(deleteAnimalsQuery).executeUpdate();
            con.createQuery(deleteSightingsQuery).executeUpdate();
            con.createQuery(deletesRangersQuery).executeUpdate();
        }
    }
}
