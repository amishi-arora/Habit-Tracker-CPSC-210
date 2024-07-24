package persistence;

import model.HabitList; 

import java.io.FileNotFoundException;

import org.json.JSONObject;

import java.io.*; 

//A class representing a writer that writes JSON representation of habit list to file

//Code based on JsonWriter class found in JsonSerializationDemo

public class JsonWriter {
    private String destFile; 
    private PrintWriter writer; 

    //EFFECTS: Creates a writer to write to the given file
    public JsonWriter(String destFile) {
        this.destFile = destFile; 
    }

    //MODIFIES: This
    //EFFECTS: Opens the writer and throws the FileNotFoundException if the file cannot be opened 
    //for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destFile)); 
    }

    //MODIFIES: This
    //EFFECTS: Writes JSON representation of given habitlist to file
    public void write(HabitList hl) {
        JSONObject jo = hl.toJson(); 
        saveToFile(jo.toString(4)); 
    }

    //MODIFIES: This
    //EFFECTS: Closes the writer
    public void close() {
        writer.close(); 
    }

    //MODIFIES: This
    //EFFECTS: Writes the given string to file
    public void saveToFile(String json) {
        writer.print(json); 
    }

}
