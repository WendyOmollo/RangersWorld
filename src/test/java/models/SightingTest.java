package models;


import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SightingTest {
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
    Sighting.clearAllSightings();

    }
    @AfterClass
    public static void shutDown() throws Exception {
        conn.close();
   }


    public Sighting setUpNewSighting(){
        return new Sighting("At the river bank","Lulu Hassan");
    }
    public Sighting setUpAnotherSighting(){
        return new Sighting("At the pond","Maria Kalu");
    }
    public Animal setUpNewAnimal(){
        return new Animal("Tiger");
    }
    public EndangeredAnimal setUpNewEndangered(){
        return new EndangeredAnimal("Rhino","Okay","Young","Endangered");
    }

    public Animal setUpAnotherAnimal(){return new Animal("Lion");}

    public EndangeredAnimal setUpAnotherEndangered(){
        return new EndangeredAnimal("Koala","Okay","Adult","Endangered");
    }

    @Test
    public void addSighting_getsInstanceOfSighting(){
        Sighting Sighting = setUpNewSighting();
        assertTrue(Sighting instanceof Sighting);
    }
    @Test
    public void addSighting_getLocation(){
        Sighting Sighting = setUpNewSighting();
        assertEquals("At the river bank",Sighting.getLocation());
    }
    @Test
    public void addSighting_getRangerName(){
        Sighting Sighting = setUpNewSighting();
        assertEquals("Lulu Hassan",Sighting.getRanger_name());
    }

    @Test
    public void addSighting_getAllInstancesOfSightings(){
        Sighting Sighting = setUpNewSighting();
        Sighting.add();
        Sighting anotherSighting = setUpAnotherSighting();
        anotherSighting.add();
        assertEquals(true, Sighting.getAll().get(0).equals(Sighting));
        assertEquals(true, Sighting.getAll().get(1).equals(anotherSighting));
    }
    @Test
    public void addSighting_getSightingById(){
        Sighting Sighting = setUpNewSighting();
        Sighting.add();
        Sighting anotherSighting = setUpAnotherSighting();
        anotherSighting.add();
        assertEquals(Sighting.findById(anotherSighting.getId()), anotherSighting);

    }
    @Test
    public void save_assignsIdToObject() {
        Sighting Sighting = setUpNewSighting();
        Sighting.add();
        Sighting savedSighting = Sighting.getAll().get(0);
        assertEquals(Sighting.getId(), savedSighting.getId());
    }
    @Test
    public void getAnimals_retrievesAllAnimalsFromDatabase_AnimalsList() {
        Sighting testSighting = setUpNewSighting();
        testSighting.add();
        Animal firstAnimal = setUpNewAnimal();
        firstAnimal.save();
        Animal secondAnimal = setUpAnotherAnimal();
        secondAnimal.save();
        Animal[] Animals = new Animal[] {  firstAnimal, secondAnimal };
        assertTrue(testSighting.getAnimals().containsAll(Arrays.asList(Animals)));
    }
    @Test
    public void getEndangeredAnimals_retrievesAllAnimalsFromDatabase_endangeredList() {
        Sighting testSighting = setUpNewSighting();
        testSighting.add();
        EndangeredAnimal firstAnimal = setUpNewEndangered();
        firstAnimal.save();
        EndangeredAnimal secondAnimal = setUpAnotherEndangered();
        secondAnimal.save();
        EndangeredAnimal[] Animals = new EndangeredAnimal[] {  firstAnimal, secondAnimal };
        assertTrue(testSighting.allEndangered().containsAll(Arrays.asList(Animals)));
    }

}