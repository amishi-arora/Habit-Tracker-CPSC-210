package model;

// A class representing a habit added to the habit tracker

public class Habit {
    String habitName;
    int daysCompleted; 
    boolean habitCompleted; 
    String checkmark; 

    //EFFECTS: Creates a habit with given name. Initially marks habit as incomplete 
    //and sets days completed to 0. Intially has checkmark as blank. 
    public Habit(String habitName) {
        this.habitName = habitName; 
        habitCompleted = false; 
        daysCompleted = 0; 
        checkmark = ""; 
    }

    //MODIFIES: this
    //EFFECTS: marks habit as complete and adds one to the number of days habit has been 
    //completed. Sets checkmark value to a check. 
    public void markHabitAsComplete() {
        habitCompleted = true; 
        daysCompleted++; 
        checkmark = " ✓"; 
    }

    //MODIFIES: this
    //EFFECTS: marks habit as incomplete and adds one to the number of days habit has been 
    //completed. Sets checkmark to blank. 
    public void markHabitAsIncomplete() {
        habitCompleted = false; 
        daysCompleted--;   
        checkmark = "";      
    }

    //EFFECTS: returns the number of days the habit has been completed 
    public int getDaysCompleted() {
        return daysCompleted; 
    }

    //EFFECTS: Returns habits name 
    public String getHabitName() {
        return this.habitName; 
    }

    //EFFECTS: Returns the habits name along with the current checkmark value
    public String getHabitAndMark() {
        return getHabitName() + checkmark; 
    }

    //EFFECTS: Returns true if habit has been marked as complete, and false if it
    //hasn't been marked as complete 
    public boolean getHabitStatus() {
        return this.habitCompleted; 
    }



}
