package models;
import org.sql2o.*;

import java.util.List;

import java.util.Objects;

import static models.DB.sql2o;

public  class Animal {

    public int id;
    public String animalName;
    public String type;

    public static final String ANIMAL_TYPE ="thriving";

    public Animal( String animalName){
        this.id = id;

        this.animalName = animalName;
        this.type = ANIMAL_TYPE;
        }

        public Animal(String animalName,int id){
            this.id = id;

            this.animalName = animalName;

        }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
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
            String sql = "INSERT INTO animals (name) VALUES (:name)";
            this.id = (int) con.createQuery(sql,true)
                    .addParameter("name", animalName)
                    .executeUpdate()
                    .getKey();
            }
        }
    public static List<Animal> all() {
        String sql = "SELECT * FROM animals";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Animal.class);
        }
    }

    public static Animal find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            Animal animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Animal.class);
            return animal;
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
