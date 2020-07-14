package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class RangerTest {

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
}