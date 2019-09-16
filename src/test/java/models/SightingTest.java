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

    @Test
    public void addSighting_getsInstanceOfSighting(){
        Sighting sighting = setUpNewSighting();
        assertTrue(sighting instanceof Sighting);
    }

}