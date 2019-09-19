package models;

import org.junit.rules.ExternalResource;
import org.sql2o.*;


public class DatabaseRule extends ExternalResource {

    @Override
    public void before() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/animal_wild", "moringa", "hyperloop");
    }

    @Override
    public void after(){
        try(Connection con = DB.sql2o.open()) {
            String deleteAnimalQuery = "DELETE FROM animals *;";
            String deleteEndangeredAnimalQuery = "DELETE FROM endangered_animals*;";
            String deleteSightingQuery = "DELETE FROM sightings *;";
            con.createQuery(deleteAnimalQuery).executeUpdate();
            con.createQuery(deleteEndangeredAnimalQuery).executeUpdate();
            con.createQuery(deleteSightingQuery).executeUpdate();
        }
    }


}
