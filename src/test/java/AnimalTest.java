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
    @Test
    public void addAnimal_true(){
        Animal animal = setUpNewAnimal();
        assertTrue(animal instanceof Animal);
    }
}
