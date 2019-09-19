package models;

import dao.SightingDao;
import org.sql2o.*;

import java.util.List;
import java.util.Objects;

import static models.DB.sql2o;

public class Sighting implements SightingDao {

    private int id;
    private String location;
    private String ranger_name;
    private  int animal_id;
    private int endangered_id;

    public Sighting(String location,String ranger_name,int animal_id){
        this.location = location;
        this.ranger_name = ranger_name;
        this.animal_id = animal_id;
        this.endangered_id =endangered_id;
        this.id = id;
    }

    public int getEndangered_id() {
        return endangered_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRanger_name() {
        return ranger_name;
    }

    public void setRanger_name(String ranger_name) {
        this.ranger_name = ranger_name;
    }

    public int getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(int animal_id) {
        this.animal_id = animal_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sighting sighting = (Sighting) o;
        return getId() == sighting.getId() &&
                getAnimal_id() == sighting.getAnimal_id() &&
                Objects.equals(getLocation(), sighting.getLocation()) &&
                Objects.equals(getRanger_name(), sighting.getRanger_name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLocation(), getRanger_name(), getAnimal_id());
    }


    public    List<Sighting> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM sightings") //raw sql
                    .executeAndFetch(Sighting.class); //fetch a list
        }

    }

//    public static List<Animal> getAll() {
//        String sql = "SELECT * FROM animals";
//        try(Connection con = sql2o.open()) {
//            return con.createQuery(sql).executeAndFetch(Animal.class);
//        }
//    }

    @Override
    public void add(Sighting sighting) {
        try(Connection con = sql2o.open()) {
            String sql = "INSERT INTO sightings (location,animal_id,ranger_name,endangered_id) VALUES (:location,:animal_id,:ranger_name,:endangered_id)";
            this.id = (int) con.createQuery(sql,true)
                    .addParameter("location", location)
                    .addParameter("animal_id",animal_id)
                    .addParameter("ranger_name", ranger_name)
                    .addParameter("endangered_id",endangered_id)
                    .executeUpdate()
                    .getKey();
        }

    }

    @Override
    public  Sighting findById(int id) {
        try(Connection con = sql2o.open()) {
            String sql = "SELECT * FROM sightings where id=:id";
            Sighting sighting = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sighting.class);
            return sighting;
        }

    }

    @Override
    public void deleteById(int id) {
        try(Connection con = sql2o.open()) {
            String sql = "DELETE * FROM sightings where id=:id";
            Sighting sighting = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sighting.class);
        }catch (Sql2oException ex){
            System.out.println(ex);
     }

    }

    @Override
    public  void clearAllSightings() {
        try (Connection con = sql2o.open()) {
            String sql = "DELETE from sightings";
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }

    }


//    public static List<EndangeredAnimal> allEndangered() {
//        String sql = "SELECT * FROM endangered_animals";
//        try(Connection con = sql2o.open()) {
//            return con.createQuery(sql).executeAndFetch(EndangeredAnimal.class);
//        }
//    }

}
