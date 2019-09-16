package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

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
        sighting.save();
        Sighting anotherSighting = setUpAnotherSighting();
        anotherSighting.save();
        assertEquals(true,Sighting.all().get(0).equals(sighting));
        assertEquals(true,Sighting.all().get(1).equals(anotherSighting));
    }

}