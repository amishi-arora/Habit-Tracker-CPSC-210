package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HabitListTest {
    HabitList hl; 
    HabitList hl2; 

    Habit h1; 
    Habit h2; 

    @BeforeEach
    void runBefore() {
        hl = new HabitList(); 
        h1 = new Habit("test"); 
        h2 = new Habit("test2"); 
    }

    @Test
    void testConstructor() {
        assertTrue(hl.getHabits().isEmpty()); 
    }

    @Test
    void testAddHabit() {
        assertEquals(0, hl.getHabits().size());
        hl.addHabit(h1); 
        assertEquals(1, hl.getHabits().size()); 
        hl.addHabit(h2); 
        assertEquals(2, hl.getHabits().size()); 
    }


    @Test
    void testRemoveHabit() {
        hl.addHabit(h1);
        hl.addHabit(h2); 
        assertEquals(2, hl.getHabits().size()); 
        hl.removeHabit(h2); 
        assertEquals(1, hl.getHabits().size()); 
        hl.removeHabit(h1); 
        assertEquals(0, hl.getHabits().size());
    }

    @Test
    void testFindHabit() {
        hl.addHabit(h1);
        hl.addHabit(h2); 

        assertEquals(h1, hl.findHabit("test")); 
        assertNull(hl.findHabit("not here")); 
    }

    @Test 
    void testCheckOffHabit() {
        hl.addHabit(h1);
        hl.addHabit(h2); 
        hl.checkOffHabit("test"); 
        assertTrue(h1.getHabitStatus()); 
        assertFalse(h2.getHabitStatus()); 
    }

    @Test 
    void testGetHabits() {
        ArrayList<Habit> testList = new ArrayList<Habit>(); 
        hl.addHabit(h1);
        hl.addHabit(h2); 
        testList.add(h1); 
        testList.add(h2); 
        assertEquals(testList, hl.getHabits()); 
    }

}
