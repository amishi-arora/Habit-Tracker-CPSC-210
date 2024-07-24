package persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.Habit;
import model.HabitList;

//Code based on JsonWriter class found in JsonSerializationDemo

public class JsonWriterTest extends JsonTest {

    @Test
    void testEmptyHabitList() {
        try {
            HabitList hl = new HabitList(); 
            JsonWriter w = new JsonWriter("./data/testWriterEmptyHabitList.json"); 
            w.open();
            w.write(hl); 
            w.close(); 
            JsonReader r = new JsonReader("./data/testWriterEmptyHabitList.json"); 
            hl = r.readHabits(); 
            assertEquals(0, hl.getHabits().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown"); 
        }

    }

    @Test
    void testNormalHabitList() {
        try {
            HabitList hl = new HabitList(); 
            Habit h1 = new Habit("sleep"); 
            Habit h2 = new Habit("do homework"); 
            h1.markHabitAsIncomplete();
            h2.markHabitAsComplete();
            hl.addHabit(h1);
            hl.addHabit(h2);
            JsonWriter w = new JsonWriter("./data/testWriterNormalHabitList.json"); 
            w.open(); 
            w.write(hl); 
            w.close(); 

            JsonReader r = new JsonReader("./data/testWriterNormalHabitList.json"); 
            hl = r.readHabits(); 
            List<Habit> habits = hl.getHabits(); 
            assertEquals(2, habits.size()); 
            checkHabit("sleep", false, habits.get(0)); 
            checkHabit("do homework", true, habits.get(1)); 

        } catch (IOException e) {
            fail("Could not read from file"); 
        }
    }

}