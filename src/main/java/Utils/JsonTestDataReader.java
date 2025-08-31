package Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonTestDataReader {

	public static JSONArray getUsersArrayFromFile(String filename) {
	    try {
	        String content = new String(Files.readAllBytes(Paths.get("Resource/TestData/"+filename)));
	        return new JSONArray(content);
	    } catch (IOException e) {
	        throw new RuntimeException("Failed to read JSON file", e);
	    }
	}
	
	public static JSONObject getJsonObjectFromFile(String filename) {
	    try {
	        String content = new String(Files.readAllBytes(Paths.get("Resource/TestData/" + filename)));
	        return new JSONObject(content);
	    } catch (IOException e) {
	        throw new RuntimeException("Failed to read JSON file", e);
	    }
	}
   
}
