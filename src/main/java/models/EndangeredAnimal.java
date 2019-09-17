package models;

import java.util.Objects;

public class EndangeredAnimal extends Animal{
    private String type;
    private int id;

        private String name;
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
        if (!super.equals(o)) return false;
        EndangeredAnimal that = (EndangeredAnimal) o;
        return getId() == that.getId() &&
                Objects.equals(getType(), that.getType()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getHealth(), that.getHealth()) &&
                Objects.equals(getAge(), that.getAge());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getType(), getId(), getName(), getHealth(), getAge());
    }
}
