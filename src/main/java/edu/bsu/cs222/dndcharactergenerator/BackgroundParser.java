package edu.bsu.cs222.dndcharactergenerator;

import com.google.gson.*;

import java.io.*;

public class BackgroundParser {
    public Background[] backgrounds=new Background[]{};
    public void setBackgroundFromJson() throws IOException {

        Gson gson = new Gson();
        backgrounds = gson.fromJson(new FileReader( "build/resources/main/backgrounds.json"),Background[].class);
    }
}


