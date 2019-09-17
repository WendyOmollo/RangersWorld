package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class EndangeredAnimalTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Rule
    public  DatabaseRule database = new DatabaseRule();

    public EndangeredAnimal setUpNewEndangered(){
        return new EndangeredAnimal(1,"Rhino","Okay","young");
    }

    @Test
    public void addEndangeredAnimal_true(){
        EndangeredAnimal weak = setUpNewEndangered();
        assertTrue(weak instanceof EndangeredAnimal);
    }
}