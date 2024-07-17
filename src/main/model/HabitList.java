package model;

import java.util.ArrayList;

// A class representing the habits that have been added to the tracker

public class HabitList {

    private ArrayList<Habit> habits; 
    
    //EFFECTS: Creates a habit list with no habits added
    public HabitList() {
        habits = new ArrayList<Habit>(); 
    }

    //MODIFIES: this
    //EFFECTS: adds given habit to the habit list
    public void addHabit(Habit h) {  
        habits.add(h); 
    }

    //MODIFIES: this
    //EFFECTS: deletes given habit from habit list
    public void removeHabit(Habit h) {  
        habits.remove(h); 
    }


    //EFFECTS: Finds habit matching entered name
    //         If habit is not found, returns null. 
    //         If there are multiple habits with the same name, returns first one

    public Habit findHabit(String h) {
        for(Habit hab: habits) {
            if(h.equals(hab.getHabitName())) {
                return hab; 
            }
        }
        return null; 
    }

    //EFFECTS: Finds habit matching name and 
    // `       marks it as complete. If theres multiple habits with the 
    //         same name, checks off the first one in the list
    public void checkOffHabit(String h) {
        Habit hab = findHabit(h); 
        hab.markHabitAsComplete();
    }

    //EFFECTS: Returns list of habits
    public ArrayList<Habit> getHabits() {
        return habits; 
    }
}
