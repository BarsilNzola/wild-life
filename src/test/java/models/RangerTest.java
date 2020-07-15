package models;

import org.junit.*;

import static org.junit.Assert.*;

public class RangerTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void rangerInstantiates_correctly() {
        Ranger testRanger = new Ranger("John", "0707159461", 45);
        assertEquals(true, testRanger instanceof Ranger);
    }

    @Test
    public void getName_rangerInstantiatesWithName_Barsil() {
        Ranger testRanger = new Ranger("John", "0707159461", 45);
        assertEquals("John", testRanger.getName());
    }

    @Test
    public void getNumber_rangerInstantiatesWithNumber() {
        Ranger testRanger = new Ranger("John", "0707159461", 45);
        assertEquals("0707159461", testRanger.getNumber());
    }

    @Test
    public void getBadgeNumber_rangerInstantiatesWithBadgeNumber() {
        Ranger testRanger = new Ranger("John", "0707159461", 45);
        assertEquals(45, testRanger.getBadgeNumber());
    }


    @Test
    public void equals_returnsTrueIfInstancesAreSame() {
        Ranger testRanger = new Ranger("John", "0707159461", 45);
        Ranger secondRanger = new Ranger("John", "0707159461", 45);
        assertTrue(testRanger.equals(secondRanger));
    }

    @Test
    public void save_insertsObjectIntoDatabase_Ranger() {
        Ranger testRanger = new Ranger("John", "0707159461", 45);
        testRanger.save();
        assertTrue(Ranger.all().get(0).equals(testRanger));
    }

    @Test
    public void all_returnsAllInstancesOfRanger_true() {
        Ranger testRanger = new Ranger("John", "0707159461", 45);
        testRanger.save();
        Ranger secondRanger = new Ranger("Jackqueen", "0718556386", 46);
        secondRanger.save();
        assertEquals(true, Ranger.all().get(0).equals(testRanger));
        assertEquals(true, Ranger.all().get(1).equals(secondRanger));
    }

    @Test
    public void save_assignsIdToObject() {
        Ranger testRanger = new Ranger("John", "0707159461", 45);
        testRanger.save();
        Ranger savedRanger = Ranger.all().get(0);
        assertEquals(testRanger.getId(), savedRanger.getId());
    }
}