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
    @Test
    public void addEndangeredAnimal_returnsTheSameAnimal(){
        EndangeredAnimal newEndangered = setUpNewEndangered();
        EndangeredAnimal anotherEndangered = setUpNewEndangered();
        assertTrue(newEndangered.equals(anotherEndangered));
    }
    @Test
    public void addEndangeredAnimal_getType(){
        EndangeredAnimal newEndangered = setUpNewEndangered();
        assertEquals(newEndangered.getType(),(EndangeredAnimal.ANIMAL_TYPE));

    }
    @Test
    public void addEndangeredAnimal_getIll(){
        EndangeredAnimal newEndangered = setUpNewEndangered();
        assertEquals(newEndangered.getAnimalIll(),EndangeredAnimal.ANIMAL_ILL);
    }

    @Test
    public void addEndangeredAnimal_getOkay(){
        EndangeredAnimal newEndangered = setUpNewEndangered();
        assertEquals(newEndangered.getAnimalOkay(),EndangeredAnimal.ANIMAL_OKAY);
    }
    @Test
    public void addEndangeredAnimal_getYoung(){
        EndangeredAnimal newEndangered = setUpNewEndangered();
        assertEquals(newEndangered.getAnimalYoung(),EndangeredAnimal.ANIMAL_YOUNG);
    }
    @Test
    public void addEndangeredAnimal_getAdult(){
        EndangeredAnimal newEndangered = setUpNewEndangered();
        assertEquals(newEndangered.getAnimalAdult(),EndangeredAnimal.ANIMAL_ADULT);
    }
    @Test
    public void addEndangeredAnimal_getNewborn(){
        EndangeredAnimal newEndangered = setUpNewEndangered();
        assertEquals(newEndangered.getAnimalNewborn(),EndangeredAnimal.ANIMAL_NEWBORN);
    }
    @Test
    public void addEndangeredAnimal_saveEndangeredAnimal(){
        EndangeredAnimal newEndangered = setUpNewEndangered();
        newEndangered.save();
        assertEquals(true,EndangeredAnimal.allEndangered().get(0).equals(newEndangered));
    }
    @Test
    public void addEndangeredAnimal_getAllInstancesAOfAnimal(){
        EndangeredAnimal newEndangered = setUpNewEndangered();
        newEndangered.save();
        EndangeredAnimal anotherEndangered = new EndangeredAnimal(2,"Elephant","Okay","Adult");
        anotherEndangered.save();
        assertEquals(true,EndangeredAnimal.allEndangered().get(0).equals(newEndangered));
        assertEquals(true,EndangeredAnimal.allEndangered().get(1).equals(anotherEndangered));
    }
    @Test
    public void save_assignsIdToObject() {
        EndangeredAnimal newEndangered = setUpNewEndangered();
        newEndangered.save();
        EndangeredAnimal savedEndangered = EndangeredAnimal.allEndangered().get(0);
        assertEquals(newEndangered.getId(), savedEndangered.getId());
    }
    @Test
    public void find_returnsAnimalWithSameId_secondAnimal() {
        EndangeredAnimal firstAnimal = setUpNewEndangered();
        firstAnimal.save();
        EndangeredAnimal secondAnimal = new EndangeredAnimal(2,"Elephant","Okay","Adult");
        secondAnimal.save();
        assertEquals(EndangeredAnimal.find(secondAnimal.getId()), secondAnimal);
    }

}