package model;

// A class representing a habit added to the habit tracker

public class Habit {
    String habitName;
    int daysCompleted; 
    boolean habitCompleted; 

    //EFFECTS: Creates a habit with given name. Initially marks habit as incomplete. 
    public Habit(String habitName) {
        this.habitName = habitName; 
        habitCompleted = false; 
    }

    //MODIFIES: this
    //EFFECTS: marks habit as complete and adds one to the number of days habit has been 
    //completed
    public void markHabitAsComplete() {
        habitCompleted = true; 
        daysCompleted ++; 
    }

    //MODIFIES: this
    //EFFECTS: marks habit as incomplete and adds one to the number of days habit has been 
    //completed
    public void markHabitAsIncomplete() {
        habitCompleted = false; 
        daysCompleted --; 
    }

    //EFFECTS: returns the number of days the habit has been completed 
    public int getDaysCompleted() {
        return daysCompleted; 
    }

    //EFFECTS: Returns habits name 
    public String getHabitName() {
        return this.habitName; 
    }

    //EFFECTS: Returns true if habit has been marked as complete, and false if it
    //         hasn't been marked as complete 
    public boolean getHabitStatus() {
        return this.habitCompleted; 
    }


}
