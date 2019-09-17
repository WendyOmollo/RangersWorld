package models;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Objects;

public  class Animal {

    private int id;
    private String name;
    private String type;
    public static final String ANIMAL_TYPE ="thriving";

    public Animal(int id,String name){
        this.id = id;
        this.name = name;
        this.type = ANIMAL_TYPE;
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
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

    public void save(){
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (id,name) VALUES (:id, :name)";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("name", name)
                    .executeUpdate();
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
    }
