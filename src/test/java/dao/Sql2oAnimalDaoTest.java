package dao;

import models.Animal;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class Sql2oAnimalDaoTest{
    private Sql2oAnimalDao animalDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        animalDao = new Sql2oAnimalDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    public Animal setUpNewAnimal(){
        return new Animal(1,"Tiger");
    }

    @Test
    public void addingAnimalSetsId() throws Exception{
        Animal animal = setUpNewAnimal();
        int originalAnimalId = animal.getId();
        animalDao.add(animal);
        assertNotEquals(originalAnimalId,animal.getId());
    }
    @Test
    public void existingAnimalsCanBeFoundById() throws Exception{
        Animal animal = setUpNewAnimal();
        animalDao.add(animal);
        Animal foundAnimal = animalDao.findById(animal.getId());
        assertEquals(animal,foundAnimal);
    }
    @Test
    public void addedAnimalsAreReturnedFromgetAll() throws Exception {
        Animal animal = setUpNewAnimal();
        animalDao.add(animal);
        assertEquals(1, animalDao.getAll().size());
    }
    @Test
    public void noAnimalsReturnsEmptyList() throws Exception {
        assertEquals(0, animalDao.getAll().size());
    }
}