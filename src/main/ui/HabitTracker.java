package ui;

import java.util.ArrayList;

import model.Habit;

public class HabitTracker {
    private ArrayList<Habit> habits;

    //EFFECTS: Creates a new instance of a habit tracker
    public HabitTracker(ArrayList<Habit> habits) {
        this.habits = habits; 
    }

    //MODIFIES: this
    //EFFECTS: Adds given habit to the habit tracker
    public void addHabit(Habit h) {
        habits.add(h);
    }

    //MODIFIES: this
    //EFFECTS: Removes given habit from the habit tracker
    public void removeHabit(Habit h) {
        habits.remove(h);
    }

    //MODIFIES: Habit
    //EFFECTS: Marks the given habit as completed
    public void checkOffHabit(Habit h) {
        h.markHabitAsComplete();
    }

    //EFFECTS: Finds habit matching entered name 
    public void findHabit(String n) {

    }

    //EFFECTS: Lists out habits that have been added to the tracker
    public void viewHabits(HabitTracker hb) {

    }
}
