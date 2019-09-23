package models;

import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;


import static org.junit.Assert.*;

public class AnimalTest{
    private static Connection conn;
    @Rule
    public  DatabaseRule database = new DatabaseRule();

    @Before
    public void setUp() throws Exception {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/animal_wild", "moringa", "hyperloop");
        conn = DB.sql2o.open();

    }

    @After
    public void tearDown() throws Exception {
        Animal.clearAllAnimals();

    }
    @AfterClass
    public static void shutDown() throws Exception {
        conn.close();
    }
    public Animal setUpNewAnimal(){
        return new Animal("Tiger");
    }
    public Animal setUpAnotherAnimal(){return new Animal ("Lion");}
    public Sighting setUpNewSighting(){
        return new Sighting("At the river bank","Lulu Hassan");
    }
    @Test
    public void addAnimal_true(){
        Animal animal = setUpNewAnimal();
        assertTrue(animal instanceof Animal);
    }

    @Test
    public void addAnimal_getName(){
        Animal animal=setUpNewAnimal();
        assertEquals("Tiger",animal.getName());
    }
    @Test
    public void equals_returnsTrueIfFirstAnimalAndSecondAnimalAreSame_true() {
        Animal firstAnimal = setUpNewAnimal();
        Animal anotherAnimal = setUpNewAnimal();
        assertEquals(true,firstAnimal.equals(anotherAnimal));
    }
    @Test
    public void all_returnsAllInstancesOfAnimal_true() {
        Animal firstAnimal = setUpNewAnimal();
        firstAnimal.save();
        assertEquals(true, Animal.all().get(0).equals(firstAnimal));
        Animal secondAnimal = setUpAnotherAnimal();
        secondAnimal.save();
        assertEquals(true, Animal.all().get(1).equals(secondAnimal));
    }

    @Test
    public void save_assignsIdToObject() {
        Animal testAnimal = setUpNewAnimal();
        testAnimal.save();
        Animal savedAnimal = Animal.all().get(0);
        assertEquals(testAnimal.getId(), savedAnimal.getId());
    }

    @Test
    public void find_returnsAnimalWithSameId_secondAnimal() {
        Animal firstAnimal = setUpNewAnimal();
        firstAnimal.save();
        Animal secondAnimal = setUpAnotherAnimal();
        secondAnimal.save();
        assertEquals(Animal.find(secondAnimal.getId()), secondAnimal);
    }

    @Test
    public void animal_instantiatesWithType_(){
        Animal animal = setUpAnotherAnimal();
        assertEquals(animal.getType(),(Animal.ANIMAL_TYPE));
    }



}
