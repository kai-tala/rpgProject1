package com.datastructures;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CharacterTest extends ObjectNotOwnedException{
    public CharacterTest() {
    }

    /**
     * Test of getName method, of class Character.
     */
    @Test
    public void testGetName() throws FileNotFoundException {
        System.out.println("getName");
        Character instance = new Character("char1.txt");
        String expResult = "Jaque";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Character.
     */
    @Test
    public void testSetName() throws FileNotFoundException {
        System.out.println("setName");
        String _name = "Bob";
        Character instance = new Character("char1.txt");
        instance.setName(_name);
        assertEquals(_name, instance.getName());
    }

    /**
     * Test of obtain method, of class Character.
     */
    @Test
    public void testObtain() {
        System.out.println("obtain");
        String thing = "Sweat Band";
        Character instance = new Character();
        assertEquals(0, instance.count(thing));
        instance.obtain(thing);
        assertEquals(1, instance.count(thing));
        instance.obtain(thing);
        assertEquals(2, instance.count(thing));
    }

    /**
     * Test of consume method, of class Character.
     */
    @Test
    public void testConsume() throws Exception {
        System.out.println("consume");
        String thing = "protein shake";
        Character instance = new Character("char1.txt");
        assertEquals(1, instance.count(thing));
        instance.consume(thing);
        assertEquals(0, instance.count(thing));
    }

    @Test
    public void testConsumeException() {
        Character c = new Character();
        Assertions.assertThrows(ObjectNotOwnedException.class, () -> c.consume("anything"));
    }

    /**
     * Test of count method, of class Character.
     */
    @Test
    public void testCount() {
        System.out.println("count");
        String thing = "sweat band";
        Character instance = new Character();
        int expResult = 0;
        int result = instance.count(thing);
        assertEquals(expResult, result);
        instance.obtain(thing);
        assertEquals(1, instance.count(thing));
    }

    /**
     * Test of addWin method, of class Character.
     */
    @Test
    public void testAddWin() {
        System.out.println("addWin");
        String event = "woo";
        Character instance = new Character();
        for (int i = 0; i < 10; i++) {
            assertEquals(i, instance.countWins(event));
            instance.addWin(event);
        }
    }

    /**
     * Test of addLoss method, of class Character.
     */
    @Test
    public void testAddLoss() {
        System.out.println("addLoss");
        String event = "woo";
        Character instance = new Character();
        for (int i = 0; i < 10; i++) {
            assertEquals(i, instance.countLosses(event));
            instance.addLoss(event);
        }
    }

    /**
     * Test of readCharacterSheet method, of class Character.
     */
    @Test
    public void testReadCharacterSheetNameInventory() throws Exception {
        String filename = "char1.txt";
        Character instance = new Character();
        instance.readCharacterSheet(filename);
        assertEquals(instance.getName(), "Jaque");
        assertEquals(1, instance.count("protein shake"));
    }

    @Test
    public void testEarnAchievement() {
        Character c = new Character();
        assertEquals(true, c.earnAchievement("hooray!"));
        assertEquals(false, c.earnAchievement("hooray!"));
    }
}
