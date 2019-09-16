package models;

import models.Animal;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class AnimalTest{
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }
    public Animal setUpNewAnimal(){
        return new Animal(1,"Tiger");
    }
    @Test
    public void addAnimal_true(){
        Animal animal = setUpNewAnimal();
        assertTrue(animal instanceof Animal);
    }
//
    @Test
    public void addAnimal_getId(){
        Animal animal = setUpNewAnimal();
        assertEquals(1,animal.getId());
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
        assertTrue(firstAnimal.equals(anotherAnimal));
    }
}
