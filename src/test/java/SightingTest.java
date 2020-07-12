import org.junit.*;
import static org.junit.Assert.*;

public class SightingTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void sighting_instantiatesCorrectly_true() {
        Sighting testSighting = new Sighting("ZoneA", "Barsil", 1);
        assertEquals(true, testSighting instanceof Sighting);
    }

    @Test
    public void getLocation_sightingInstantiatesWithLocation_zoneA() {
        Sighting testSighting = new Sighting("ZoneA", "Barsil", 1);
        assertEquals("ZoneA", testSighting.getLocation());
    }

    @Test
    public void getRangerName_sightingInstantiatesWithRangerName_Barsil() {
        Sighting testSighting = new Sighting("ZoneA", "Barsil", 1);
        assertEquals("Barsil", testSighting.getRangerName());
    }

    @Test
    public void getAnimalId_sightingInstantiatesWithAnimalId_1() {
        Sighting testSighting = new Sighting("ZoneA", "Barsil", 1);
        assertEquals(1, testSighting.getAnimalId());
    }

    @Test
    public void equals_returnsTrueIfInstancesAreSame() {
        Sighting testSighting = new Sighting("ZoneA", "Barsil", 1);
        Sighting secondSighting = new Sighting("ZoneA", "Barsil", 1);
        assertTrue(testSighting.equals(secondSighting));
    }

    @Test
    public void save_insertsObjectIntoDatabase_Sighting() {
        Sighting testSighting = new Sighting("ZoneA", "Barsil", 1);
        testSighting.save();
        assertTrue(Sighting.all().get(0).equals(testSighting));
    }

    @Test
    public void all_returnsAllInstancesOfSighting_true() {
        Sighting testSighting = new Sighting("ZoneA", "Barsil", 1);
        testSighting.save();
        Sighting secondSighting = new Sighting("ZoneA", "Barsil", 2);
        secondSighting.save();
        assertEquals(true, Sighting.all().get(0).equals(testSighting));
        assertEquals(true, Sighting.all().get(1).equals(secondSighting));
    }

    @Test
    public void save_assignsIdToObject() {
        Sighting testSighting = new Sighting("ZoneA", "Barsil", 1);
        testSighting.save();
        Sighting savedSighting = Sighting.all().get(0);
        assertEquals(testSighting.getId(), savedSighting.getId());
    }

    @Test
    public void find_returnsSightingWithSameId_secondSighting() {
        Sighting testSighting = new Sighting("ZoneA", "Barsil", 1);
        testSighting.save();
        Sighting secondSighting = new Sighting("ZoneA", "Barsil", 2);
        secondSighting.save();
        assertEquals(Sighting.find(secondSighting.getId()), secondSighting);
    }
}