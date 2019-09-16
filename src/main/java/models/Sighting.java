package models;

import org.sql2o.*;

import java.util.List;
import java.util.Objects;

public class Sighting {

    private int id;
    private String location;
    private String ranger_name;
    private  int animal_id;

    public Sighting(String location,String ranger_name,int animal_id){
        this.location = location;
        this.ranger_name = ranger_name;
        this.animal_id = animal_id;
        this.id = id;
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

    public void saveSighting(){
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (location,animal_id,ranger_name) VALUES (:location,:animal_id,:ranger_name)";
            this.id = (int) con.createQuery(sql,true)
                    .addParameter("location", location)
                    .addParameter("animal_id",animal_id)
                    .addParameter("ranger_name", ranger_name)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List<Sighting> all() {
        String sql = "SELECT * FROM sightings";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Sighting.class);
        }
    }
    public static Sighting find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings where id=:id";
            Sighting sighting = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sighting.class);
            return sighting;
        }
    }

}
