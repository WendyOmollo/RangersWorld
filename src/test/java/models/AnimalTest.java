package models;

import models.Animal;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


import static org.junit.Assert.*;

public class AnimalTest{
    @Rule
    public  DatabaseRule database = new DatabaseRule();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }
    public Animal setUpNewAnimal(){
        return new Animal(1,"Tiger");
    }
    public Animal setUpAnotherAnimal(){return new Animal (2,"Lion");}
    public Sighting setUpNewSighting(){
        return new Sighting("At the river bank","Lulu Hassan",1);
    }
    @Test
    public void addAnimal_true(){
        Animal animal = setUpNewAnimal();
        assertTrue(animal instanceof Animal);
    }

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
    @Test
    public void all_returnsAllInstancesOfAnimal_true() {
        Animal firstAnimal = setUpNewAnimal();
        firstAnimal.save();
        Animal secondAnimal = setUpAnotherAnimal();
        secondAnimal.save();
        assertEquals(true, Animal.all().get(0).equals(firstAnimal));
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
    public void save_savesAnimalIdIntoDB_true() {
        Animal testAnimal = setUpNewAnimal();
        testAnimal.save();
        Sighting testSighting = setUpNewSighting();
        testSighting.saveSighting();
        Sighting savedSighting = Sighting.find(testSighting.getId());
        assertEquals(savedSighting.getAnimal_id(), testAnimal.getId());
    }
}
