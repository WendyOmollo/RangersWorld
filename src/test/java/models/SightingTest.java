package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SightingTest {
    @Rule
    public  DatabaseRule database = new DatabaseRule();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    public Sighting setUpNewSighting(){
        return new Sighting("At the river bank","Lulu Hassan",1);
    }
    public Sighting setUpAnotherSighting(){
        return new Sighting("At the pond","Maria Kalu",2);
    }
    public Animal setUpNewAnimal(){
        return new Animal(1,"Tiger");
    }

    public Animal setUpAnotherAnimal(){return new Animal (2,"Lion");}

    @Test
    public void addSighting_getsInstanceOfSighting(){
        Sighting sighting = setUpNewSighting();
        assertTrue(sighting instanceof Sighting);
    }
    @Test
    public void addSighting_getLocation(){
        Sighting sighting = setUpNewSighting();
        assertEquals("At the river bank",sighting.getLocation());
    }
    @Test
    public void addSighting_getRangerName(){
        Sighting sighting = setUpNewSighting();
        assertEquals("Lulu Hassan",sighting.getRanger_name());
    }
    @Test
    public void addSighting_getAnimalId(){
        Sighting sighting = setUpNewSighting();
        assertEquals(1,sighting.getAnimal_id());
    }
    @Test
    public void addSighting_getAllInstancesOfSightings(){
        Sighting sighting = setUpNewSighting();
        sighting.saveSighting();
        Sighting anotherSighting = setUpAnotherSighting();
        anotherSighting.saveSighting();
        assertEquals(true,Sighting.all().get(0).equals(sighting));
        assertEquals(true,Sighting.all().get(1).equals(anotherSighting));
    }
    @Test
    public void addSighting_getSightingById(){
        Sighting sighting = setUpNewSighting();
        sighting.saveSighting();
        Sighting anotherSighting = setUpAnotherSighting();
        anotherSighting.saveSighting();
        assertEquals(Sighting.find(anotherSighting.getId()), anotherSighting);

    }
@Test
public void save_assignsIdToObject() {
    Sighting sighting = setUpNewSighting();
    sighting.saveSighting();
    Sighting savedSighting = Sighting.all().get(0);
    assertEquals(sighting.getId(), savedSighting.getId());
}
    @Test
    public void getAnimals_retrievesAllMonstersFromDatabase_monstersList() {
        Sighting testSighting = setUpNewSighting();
        testSighting.saveSighting();
        Animal firstAnimal = setUpNewAnimal();
        firstAnimal.save();
        Animal secondAnimal = setUpAnotherAnimal();
        secondAnimal.save();
        Animal[] animals = new Animal[] {  firstAnimal, secondAnimal };
        assertTrue(testSighting.getAll().containsAll(Arrays.asList(animals)));
    }

}