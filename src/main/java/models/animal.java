package models;
import org.sql2o.*;

import java.util.List;

import java.util.Objects;

import static models.DB.sql2o;

public  class animal {

    public int id;
    public String animalName;
    public String type;

    public static final String ANIMAL_TYPE ="thriving";

    public animal(String animalName){
        this.animalName = animalName;
        this.type = ANIMAL_TYPE;
        }

//        public Animal(String animalName,int id){
//            this.id = id;
//            this.animalName = animalName;
//
//        }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        animal animal = (models.animal) o;
        return getId() == animal.getId() &&
                Objects.equals(getName(), animal.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return animalName;
        }

        public void setName(String name) {
            this.animalName = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }



    public void save(){
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name,type) VALUES (:name,:type)";
            this.id = (int) con.createQuery(sql,true)
                    .addParameter("name", animalName)
                    .addParameter("type",type)
                    .executeUpdate()
                    .getKey();
            }
        }
    public static List<animal> all() {
        String sql = "SELECT * FROM animals";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(animal.class);
        }
    }

    public static animal find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            animal animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(models.animal.class);
            return animal;
            }
        }
    public void deleteById(int id) {
        try(Connection con = sql2o.open()) {
            String sql = "DELETE * FROM animals where id=:id";
            animal animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(models.animal.class);
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
    public static void clearAllAnimals() {
        String sql = "DELETE from animals";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

}
