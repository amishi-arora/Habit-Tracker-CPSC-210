package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Event class
 */

// Code from CPSC 210 alarm system project
public class EventTest {
    private Event e1;
    private Date d1;

    @BeforeEach
    public void runBefore() {
        e1 = new Event("Habit added");
        d1 = Calendar.getInstance().getTime();
    }

    @Test
    public void testEvent() {
        assertEquals("Habit added", e1.getDescription());
        assertEquals(d1, e1.getDate());
    }

    @Test
    public void testToString() {
        assertEquals(d1.toString() + "\n" + "Habit added", e1.toString());
    }

    @Test
    public void testEquals() {
        assertFalse(e1.equals(null));
    }
}