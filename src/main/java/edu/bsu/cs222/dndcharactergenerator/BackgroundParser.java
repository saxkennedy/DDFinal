package edu.bsu.cs222.dndcharactergenerator;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javafx.application.Application;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class BackgroundParser {
    @SuppressWarnings("unchecked")
    public void runParser() {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("Backgrounds.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray backgroundList = (JSONArray) jsonObject.get("Backgrounds");
            Iterator<JSONObject> iterator = backgroundList.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

