import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class AnimalTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void animal_instantiatesCorrectly_true(){
       Animal newAnimal = new Animal("cheetah", "sam", "feline");
       assertEquals(true, newAnimal instanceof Animal);
    }

    @Test
    public void getName_animalInstantiatesWithName_Cheetah() {
        Animal newAnimal = new Animal("cheetah", "sam", "feline");
        assertEquals("cheetah", newAnimal.getName());
    }

    @Test
    public void getSpecies_animalInstantiatesWithSpecies_Feline() {
        Animal newAnimal = new Animal("cheetah", "sam", "feline");
        assertEquals("feline", newAnimal.getSpecies());
    }

    @Test
    public void getNickname_animalInstantiatesWithNickName_Sam() {
        Animal newAnimal = new Animal("cheetah", "sam", "feline");
        assertEquals("sam", newAnimal.getNickname());
    }

    @Test
    public void equals_returnsTrueIfInstancesAreSame() {
        Animal newAnimal = new Animal("cheetah", "sam", "feline");
        Animal secondAnimal = new Animal("cheetah", "sam","feline");
        assertTrue(newAnimal.equals(secondAnimal));
    }

    @Test
    public void save_insertsObjectIntoDatabase_Animal() {
        Animal newAnimal = new Animal("cheetah", "sam", "feline");
        newAnimal.save();
        assertTrue(Animal.all().get(0).equals(newAnimal));
    }

    @Test
    public void all_returnsAllInstancesOfAnimal_true() {
        Animal newAnimal = new Animal("cheetah", "sam", "feline");
        newAnimal.save();
        Animal secondAnimal = new Animal("lion", "henry","feline");
        secondAnimal.save();
        assertEquals(true, Animal.all().get(0).equals(newAnimal));
        assertEquals(true, Animal.all().get(1).equals(secondAnimal));
    }

    @Test
    public void save_assignsIdToObject() {
        Animal newAnimal = new Animal("cheetah", "sam", "feline");
        newAnimal.save();
        Animal savedAnimal = Animal.all().get(0);
        assertEquals(newAnimal.getId(), savedAnimal.getId());
    }

    @Test
    public void find_returnsAnimalWithSameId_secondAnimal() {
        Animal newAnimal = new Animal("cheetah", "sam", "feline");
        newAnimal.save();
        Animal secondAnimal = new Animal("lion", "henry","feline");
        secondAnimal.save();
        assertEquals(Animal.find(secondAnimal.getId()), secondAnimal);
    }
}