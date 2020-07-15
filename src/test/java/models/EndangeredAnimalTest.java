package models;

import org.junit.*;
import static org.junit.Assert.*;

public class EndangeredAnimalTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void animal_instantiatesCorrectly_true(){
        EndangeredAnimal testAnimal = new EndangeredAnimal ("Cheetah","Sam","Feline", "young", "ill");
        assertEquals(true, testAnimal instanceof EndangeredAnimal);
    }

    @Test
    public void endangeredAnimal_getHealthInstantiatesCorrectly_String(){
        EndangeredAnimal testAnimal = new EndangeredAnimal("Cheetah","Sam","Feline", "young", "ill");
        assertEquals("ill", testAnimal.getHealth());
    }

    @Test
    public void endangeredAnimal_getAgeInstantiatesCorrectly_String(){
        EndangeredAnimal testAnimal = new EndangeredAnimal("Cheetah","Sam","Feline", "young", "ill");
        assertEquals("young", testAnimal.getAge());
    }

    @Test
    public void save_InsertsObjectIntoDatabase_animal(){
        EndangeredAnimal testAnimal = new EndangeredAnimal("Cheetah","Sam","Feline", "young", "ill");
        testAnimal.save();
        assertTrue(EndangeredAnimal.all().get(0).equals(testAnimal));
    }

    @Test
    public void all_returnsAllInstancesOfAnimals_true(){
        EndangeredAnimal firstAnimal = new EndangeredAnimal("Cheetah","Sam","Feline", "young", "ill");
        firstAnimal.save();
        EndangeredAnimal secondAnimal = new EndangeredAnimal ("Cheetah","Sam","Feline", "young", "ill");
        secondAnimal.save();
        assertEquals(true, EndangeredAnimal.all().get(0).equals(firstAnimal));
        assertEquals(true, EndangeredAnimal.all().get(1).equals(secondAnimal));
    }
    @Test
    public void save_assignsIdToObject() {
        EndangeredAnimal testAnimal = new EndangeredAnimal ("Cheetah","Sam","Feline", "young", "ill");
        testAnimal.save();
        EndangeredAnimal savedAnimal = EndangeredAnimal.all().get(0);
        assertEquals(testAnimal.getId(), savedAnimal.getId());
    }
    @Test
    public void find_returnsAnimalsWithSameId_secondAnimal() {
        EndangeredAnimal firstAnimal = new EndangeredAnimal("Cheetah","Sam","Feline", "young", "ill");
        firstAnimal.save();
        EndangeredAnimal secondAnimal = new EndangeredAnimal ("Cheetah","Sam","Feline", "young", "ill");
        secondAnimal.save();
        assertEquals(EndangeredAnimal.find(secondAnimal.getId()), secondAnimal);
    }
}