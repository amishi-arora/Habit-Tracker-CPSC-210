package model;

import java.util.ArrayList;

public class HabitTracker {
    private ArrayList<Habit> habits;

    public void addHabit(Habit h) {
        habits.add(h);
    }

    public void removeHabit(Habit h) {
        habits.remove(h);
    }

    public void checkOffHabit(Habit h) {
        h.markHabitAsCompleted();
    }




    
}
