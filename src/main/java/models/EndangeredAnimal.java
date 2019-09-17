package models;

import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class EndangeredAnimal extends Animal{

    private String type;
    private int id;

        private static String name;
        private String health;
        private String age;

        public static final String ANIMAL_TYPE = "Endangered";
        public static final String ANIMAL_ILL = "Ill";
        public static final String ANIMAL_OKAY = "Okay";
        public static final String ANIMAL_YOUNG = "Young";
        public static final String ANIMAL_NEWBORN = "Newborn";
        public static final String ANIMAL_ADULT = "Adult";



    public EndangeredAnimal(int id, String name,String health,String age) {
        super(id, name);
        this.id =id;
        this.name = name;
        this.health = health;
        this.age = age;
        this.type = ANIMAL_TYPE;

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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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
        EndangeredAnimal that = (EndangeredAnimal) o;
        return (this.name.equals(EndangeredAnimal.name));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getType(), getId(), getName(), getHealth(), getAge());
    }
    public void save(){
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO endangered_animals (id,name,health,age) VALUES (:id, :name,:health,:age)";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("name", name)
                    .addParameter("health",health)
                    .addParameter("age",age)
                    .executeUpdate();
        }
    }
    public static List<EndangeredAnimal> allEndangered() {
        String sql = "SELECT * FROM endangered_animals";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(EndangeredAnimal.class);
        }
    }
}
