package edu.bsu.cs222.dndcharactergenerator;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;

public class BackgroundParser {

    public CharacterBackground[] setBackgroundFromJson() throws IOException {
        Gson gson = new Gson();
        return gson.fromJson(new FileReader("build/resources/main/backgrounds.json"), CharacterBackground[].class);
    }
}


