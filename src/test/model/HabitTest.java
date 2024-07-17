package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HabitTest {
    
    Habit h1; 
    Habit h2; 

    @BeforeEach
    void runBefore() {
        h1 = new Habit("test"); 
        h2 = new Habit("test2"); 
    }

    @Test
    void testConstructor() {
        assertEquals("test", h1.getHabitName()); 
        assertFalse(h1.getHabitStatus()); 
        assertFalse(h1.getHabitStatus()); 
    }

    @Test
    void testMarkHabitAsComplete() {
        h1.markHabitAsComplete();
        assertTrue(h1.getHabitStatus()); 
    }

    @Test
    void testMarkHabitAsIncomplete() {
        h1.markHabitAsIncomplete();
        assertFalse(h1.getHabitStatus()); 
    }

    @Test
    void testGetDaysCompleted() {
        h1.markHabitAsComplete();
        h1.markHabitAsComplete();
        assertEquals(2, h1.getDaysCompleted());      
    }

    @Test
    void testGetHabitName() {
        assertEquals("test", h1.getHabitName()); 
        assertEquals("test2", h2.getHabitName()); 
    }

    @Test
    void testGetHabitStatus() {
        h1.markHabitAsComplete();
        assertTrue(h1.getHabitStatus()); 
        h1.markHabitAsIncomplete();
        assertFalse(h1.getHabitStatus()); 
    }
}
