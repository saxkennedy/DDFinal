package edu.bsu.cs222.dndcharactergenerator;

import com.google.gson.*;

import java.io.*;

public class BackgroundParser {

    public CharacterBackground[] setBackgroundFromJson() throws IOException {

        Gson gson = new Gson();
        CharacterBackground []backgrounds = gson.fromJson(new FileReader( "build/resources/main/backgrounds.json"), CharacterBackground[].class);
        return backgrounds;
    }
}


