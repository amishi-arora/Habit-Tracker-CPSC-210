package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// A class representing the habits that have been added to the tracker

public class HabitList implements Writable {

    private ArrayList<Habit> habits; 
    
    //EFFECTS: Creates a habit list with no habits added
    public HabitList() {
        habits = new ArrayList<Habit>(); 
    }

    //MODIFIES: This
    //EFFECTS: Adds given habit to the habit list and logs the event
    public void addHabit(Habit h) {  
        habits.add(h); 
        EventLog.getInstance().logEvent(new Event("Habit added to habit list."));
    }


    //MODIFIES: This, EventLog
    //EFFECTS: Deletes given habit from habit list and logs the event
    public void removeHabit(Habit h) {  
        habits.remove(h); 
        EventLog.getInstance().logEvent(new Event("Habit removed from habit list.")); 
    }


    //EFFECTS: Finds habit matching entered name
    //If habit is not found, returns null. 
    //If there are multiple habits with the same name, returns first one
    public Habit findHabit(String h) {
        for (Habit hab: habits) {
            if (h.equals(hab.getHabitName())) {
                return hab; 
            }
        }
        return null; 
    }

    //EFFECTS: Returns list of habits
    public ArrayList<Habit> getHabits() {
        return habits; 
    }


    //EFFECTS: Returns habits in habit list as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject(); 
        json.put("habits", habitsToJson()); 
        return json;  
    }

    //EFFECTS: Returns habits in habit list as a JSON array
    private JSONArray habitsToJson() {
        JSONArray jsonArray = new JSONArray(); 
        for (Habit h: habits) {
            jsonArray.put(h.toJson()); 
        }
        return jsonArray;
    }


}
