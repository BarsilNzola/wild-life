import org.junit.*;

import static org.junit.Assert.*;

public class AnimalTest {

    @Test
    public void animal_instantiatesCorrectly_true(){
       Animal newAnimal = new Animal("cheetah", "feline");
       assertEquals(true, newAnimal instanceof Animal);
    }

    @Test
    public void getName_animalInstantiatesWithName_Cheetah() {
        Animal newAnimal = new Animal("cheetah", "feline");
        assertEquals("cheetah", newAnimal.getName());
    }

    @Test
    public void getSpecies_animalInstantiatesWithSpecies_Feline() {
        Animal newAnimal = new Animal("cheetah", "feline");
        assertEquals("feline", newAnimal.getSpecies());
    }

}