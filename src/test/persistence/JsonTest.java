package persistence;

import static org.junit.Assert.assertEquals;

import model.Habit;

//Code based on JsonTest class found in JsonSerializationDemo

public class JsonTest {
    protected void checkHabit(String habitName, Boolean completed, Habit h) {
        assertEquals(habitName, h.getHabitName()); 
        assertEquals(completed, h.getHabitStatus()); 
    }

}
