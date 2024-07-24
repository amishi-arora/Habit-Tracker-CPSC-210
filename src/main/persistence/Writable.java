package persistence;

import org.json.JSONObject; 

//Code based on Writable interface found in JsonSerializationDemo

public interface Writable {

    //EFFECTS: returns this as JSON object
    JSONObject toJson(); 

}
