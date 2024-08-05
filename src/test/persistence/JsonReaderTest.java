package persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.Habit;
import model.HabitList;

//Code based on JsonReaderTest class found in JsonSerializationDemo

public class JsonReaderTest extends JsonTest {
    

    @Test
    void testFileDoesNotExist() {
        JsonReader r = new JsonReader("./data/doesn'tExist.json"); 
        try {
            HabitList hl = r.readHabits();
            fail("IOExceptioin expected"); 
        } catch (IOException e) {
            // runs as exptected
        }
    }

    @Test
    void testEmptyHabitList() {
        JsonReader r = new JsonReader("./data/testReaderEmptyHabitList.json"); 
        try {
            HabitList hl = r.readHabits(); 
            assertEquals(0, hl.getHabits().size()); 
        } catch (IOException e) {
            fail("Could not read from file"); 
        }

    }

    @Test
    void testNormalHabitList() {
        JsonReader r = new JsonReader("./data/testReaderNormalHabitList.json"); 
        try {
            HabitList hl = r.readHabits(); 
            List<Habit> habits = hl.getHabits(); 
            assertEquals(3, habits.size()); 
            checkHabit("sleep 8 hours", true, 1, habits.get(0)); 
            checkHabit("drink water", false, 0, habits.get(1)); 
            checkHabit("eat healthy", false, 0, habits.get(2)); 
        } catch (IOException e) {
            fail("Could not read from file"); 
        }
    }

}
