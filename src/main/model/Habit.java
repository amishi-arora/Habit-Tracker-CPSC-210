package model;

public class Habit {
    String habitName;
    int daysCompleted; 
    boolean habitCompleted; 
    char checkMark; 

    public Habit(String habitName) {
        this.habitName = habitName; 
        habitCompleted = false; 
        checkMark = '☐';
    }

    public int getDaysCompleted() {
        return daysCompleted; 
    }

    public void markHabitAsCompleted() {
        habitCompleted = true; 
        daysCompleted ++; 
        checkMark = '☑';
    }
}
