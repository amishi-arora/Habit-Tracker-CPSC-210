package persistence;

import model.Habit;
import model.HabitList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

//A class representing a reader that reads the habit tracker from the JSON data stored in file

//Code based on JsonReader class found in JsonSerializationDemo

public class JsonReader {
    private String sourceFile; 

    //EFFECTS: Creates reader that reads given source file
    public JsonReader(String sourceFile) {
        this.sourceFile = sourceFile; 
    }


    //EFFECTS: Reads the habit list stored in file and returns it
    public HabitList readHabits() throws IOException {
        String data = readSourceFile(sourceFile); 
        JSONObject jsonObject = new JSONObject(data); 
        return parceHabitList(jsonObject);

    }

    //EFFECTS: Reads the given source file and returns it as a string
    private String readSourceFile(String sourceFile) throws IOException {
        StringBuilder habitContent = new StringBuilder(); 

        try (Stream<String> st = Files.lines(Paths.get(sourceFile), StandardCharsets.UTF_8)) {
            st.forEach(s -> habitContent.append(s)); 
        }

        return habitContent.toString();

    }

    //EFFECTS: Parses the habitlist in the given JSON object and returns it
    private HabitList parceHabitList(JSONObject jo) {
        HabitList hl = new HabitList(); 
        addHabits(hl, jo); 
        return hl; 
    }

    //MODIFIES: hl
    //EFFECTS: Parses habits from JSON object and add them to habit list
    private void addHabits(HabitList hl, JSONObject jo) {
        JSONArray ja = jo.getJSONArray("habits"); 
        for (Object o: ja) {
            JSONObject nextHabit = (JSONObject) o; 
            addHabit(hl, nextHabit);
        }
    }

    //MODIFIES: hl, h
    //EFFECTS: Parses habit from JSON object and adds it to habit list 
    private void addHabit(HabitList hl, JSONObject jo) {
        String habitName = jo.getString("habitName"); 
        Boolean habitCompleted = jo.getBoolean("habitCompleted"); 
        Habit h = new Habit(habitName); 
        if (habitCompleted) {
            h.markHabitAsComplete();
        } else {
            h.markHabitAsIncomplete();
        }
        hl.addHabit(h); 
    }


}
