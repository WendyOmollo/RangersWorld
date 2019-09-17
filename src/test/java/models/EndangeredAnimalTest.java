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
        return new EndangeredAnimal(1,"Rhino","Okay","Young");
    }

    @Test
    public void addEndangeredAnimal_true(){
        EndangeredAnimal newEndangered = setUpNewEndangered();
        assertTrue(newEndangered instanceof EndangeredAnimal);
    }
    @Test
    public void addEndangeredAnimal_getId(){
        EndangeredAnimal newEndangered = setUpNewEndangered();
        assertEquals(1,newEndangered.getId());
    }
    @Test
    public void addEndangeredAnimal_getName(){
        EndangeredAnimal newEndangered = setUpNewEndangered();
        assertEquals("Rhino",newEndangered.getName());
    }
    @Test
    public void addEndangeredAnimal_getHealth(){
        EndangeredAnimal newEndangered = setUpNewEndangered();
        assertEquals("Okay",newEndangered.getHealth());
    }
    @Test
    public void addEndangeredAnimal_getAge(){
        EndangeredAnimal newEndangered = setUpNewEndangered();
        assertEquals("Young",newEndangered.getAge());
    }
}