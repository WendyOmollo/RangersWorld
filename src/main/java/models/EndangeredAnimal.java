package models;

import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;
import java.util.Objects;

import static models.DB.sql2o;

public class EndangeredAnimal extends Animal {
        private String health;
        private String age;
        private int id;

        public static final String ANIMAL_TYPE = "Endangered";
        public static final String ANIMAL_ILL = "Ill";
        public static final String ANIMAL_OKAY = "Okay";
        public static final String ANIMAL_YOUNG = "Young";
        public static final String ANIMAL_NEWBORN = "Newborn";
        public static final String ANIMAL_ADULT = "Adult";



    public EndangeredAnimal(String animalName, String health, String age, String ANIMAL_TYPE) {
        super(animalName);
        this.animalName = getName();
        this.health = health;
        this.age = age;
        this.type = ANIMAL_TYPE;
        this.id =id;
    }
    public static String getAnimalIll() {
        return ANIMAL_ILL;
    }

    public static String getAnimalOkay() {
        return ANIMAL_OKAY;
    }

    public static String getAnimalYoung() {
        return ANIMAL_YOUNG;
    }

    public static String getAnimalNewborn() {
        return ANIMAL_NEWBORN;
    }

    public static String getAnimalAdult() {
        return ANIMAL_ADULT;
    }



    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;

    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EndangeredAnimal animal = (EndangeredAnimal) o;
        return (this.animalName.equals(animal.animalName));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getType(), getId(), getName(), getHealth(), getAge());
    }
    public void save(){
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO endangered_animals (name,health,age,type) VALUES (:name,:health,:age,:type)";
           this.id=(int) con.createQuery(sql,true)
                    .addParameter("name", animalName)
                    .addParameter("health",health)
                    .addParameter("age",age)
                    .addParameter("type",type)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List<EndangeredAnimal> allEndangered() {
        String sql = "SELECT * FROM endangered_animals";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(EndangeredAnimal.class);
        }
    }
    public static EndangeredAnimal find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM endangered_animals where id=:id";
            EndangeredAnimal animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(EndangeredAnimal.class);
                        return animal;
        }
    }
    public static void clearAllEndangered() {
        String sql = "DELETE from endangered_animals";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
