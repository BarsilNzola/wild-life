package models;

import org.junit.*;
import static org.junit.Assert.*;

public class AnimalTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void animal_instantiatesCorrectly_true(){
       Animal newAnimal = new Animal("cheetah", "sam", "feline", 1);
       assertEquals(true, newAnimal instanceof Animal);
    }

    @Test
    public void getName_animalInstantiatesWithName_Cheetah() {
        Animal newAnimal = new Animal("cheetah", "sam", "feline", 1);
        assertEquals("cheetah", newAnimal.getName());
    }

    @Test
    public void getSpecies_animalInstantiatesWithSpecies_Feline() {
        Animal newAnimal = new Animal("cheetah", "sam", "feline", 1);
        assertEquals("feline", newAnimal.getSpecies());
    }

    @Test
    public void getNickname_animalInstantiatesWithNickName_Sam() {
        Animal newAnimal = new Animal("cheetah", "sam", "feline", 1);
        assertEquals("sam", newAnimal.getNickname());
    }

    @Test
    public void getSightingId_animalInstantiatesWithSightingId_1() {
        Animal newAnimal = new Animal("cheetah", "sam", "feline", 1);
        assertEquals(1, newAnimal.getSightingId());
    }

    @Test
    public void equals_returnsTrueIfInstancesAreSame() {
        Animal newAnimal = new Animal("cheetah", "sam", "feline", 1);
        Animal secondAnimal = new Animal("cheetah", "sam","feline", 1);
        assertTrue(newAnimal.equals(secondAnimal));
    }

    @Test
    public void save_insertsObjectIntoDatabase_Animal() {
        Animal newAnimal = new Animal("cheetah", "sam", "feline", 1);
        newAnimal.save();
        assertTrue(Animal.all().get(0).equals(newAnimal));
    }

    @Test
    public void all_returnsAllInstancesOfAnimal_true() {
        Animal newAnimal = new Animal("cheetah", "sam", "feline", 1);
        newAnimal.save();
        Animal secondAnimal = new Animal("lion", "henry","feline", 1);
        secondAnimal.save();
        assertEquals(true, Animal.all().get(0).equals(newAnimal));
        assertEquals(true, Animal.all().get(1).equals(secondAnimal));
    }

    @Test
    public void save_assignsIdToObject() {
        Animal newAnimal = new Animal("cheetah", "sam", "feline", 1);
        newAnimal.save();
        Animal savedAnimal = Animal.all().get(0);
        assertEquals(newAnimal.getId(), savedAnimal.getId());
    }

    @Test
    public void find_returnsAnimalWithSameId_secondAnimal() {
        Animal newAnimal = new Animal("cheetah", "sam", "feline", 1);
        newAnimal.save();
        Animal secondAnimal = new Animal("lion", "henry","feline", 1);
        secondAnimal.save();
        assertEquals(Animal.find(secondAnimal.getId()), secondAnimal);
    }

    @Test
    public void save_savesSightingIdIntoDB_true() {
        Sighting testSighting = new Sighting("ZoneA", "Barsil");
        testSighting.save();
        Animal newAnimal = new Animal("cheetah", "sam", "feline", testSighting.getId());
        newAnimal.save();
        Animal savedAnimal = Animal.find(newAnimal.getId());
        assertEquals(savedAnimal.getSightingId(), testSighting.getId());
    }
}