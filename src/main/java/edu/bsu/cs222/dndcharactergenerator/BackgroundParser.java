package edu.bsu.cs222.dndcharactergenerator;
import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

import javafx.application.Application;
import jdk.internal.module.ModuleLoaderMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class BackgroundParser {
    @SuppressWarnings("unchecked")
    public void runParser() {
        try {
            JSONParser parser = new JSONParser();
            Object obj=parser.parse(new FileReader("backgrounds.json"));

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
