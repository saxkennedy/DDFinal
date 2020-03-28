package edu.bsu.cs222.dndcharactergenerator;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class View extends Application {

    public static void main(String[] args) {
        launch((args));
    }

    @Override
    public void start(Stage stage) {
        Character character = new Character();
        Color bgColor = Color.NAVAJOWHITE;

        //***1st Name Scene***
        VBox nameVbox = new VBox();
        nameVbox.setAlignment(Pos.CENTER);
        nameVbox.setSpacing(20);
        nameVbox.setBackground(Background.EMPTY);
        Scene openAndNameScene = new Scene(nameVbox, 550, 850, bgColor);
        Image fighterImage = new Image("/dwarfImage.png", true);
        ImageView fighterImageView = new ImageView();
        fighterImageView.setImage(fighterImage);
        Label enterName = new Label("Enter your name below, bold adventurer!  Then press 'GO!'\n\n" +
                " *Leaving this blank is fine, you can always change it later!");
        TextField characterName = new TextField();
        Button go = new Button("GO!");
        nameVbox.getChildren().addAll(fighterImageView, enterName, characterName, go);


        //***2nd Core Stats/Race Selection Scene***
        VBox coreInfoVbox = new VBox();
        coreInfoVbox.setAlignment(Pos.CENTER);
        coreInfoVbox.setSpacing(10);
        coreInfoVbox.setBackground(Background.EMPTY);
        Scene coreAttributesScene = new Scene(coreInfoVbox, 550, 850, bgColor);
        Label raceSelection = new Label("Choose from one of the below races!");
        Label statGeneration = new Label("Fill out your rolled stats below.\n" +
                "We have provided a dice roller that uses standard rules,\n" +
                "Feel free to insert your own values, or take ours!");
        Button diceRoller = new Button("Roll the Dice!");
        Button nextToCombatStyle = new Button("Next (Combat Style)");
        Button backToName = new Button("Back (Name Screen)");
        HBox coreStatsButtons = new HBox(backToName, nextToCombatStyle);
        coreStatsButtons.setAlignment(Pos.CENTER);
        coreStatsButtons.setSpacing(25);
        Label rolledStats = new Label("");
        VBox coreStatsVbox = new VBox();
        coreStatsVbox.setAlignment(Pos.CENTER);
        coreStatsVbox.setSpacing(10);
        coreStatsVbox.setBackground(Background.EMPTY);
        for (String abilityName: character.statNames) {
            Label abilityLabel = new Label(abilityName);
            ComboBox<Integer> abilityInQuestion = new ComboBox();
            abilityInQuestion.getItems().addAll(character.statNumbers);
            coreStatsVbox.getChildren().addAll(abilityLabel,abilityInQuestion);
            abilityInQuestion.setOnAction(e -> character.setCharacterAttribute(AbilityScore.getAbilityScoreFromString(abilityName),abilityInQuestion.getValue()));
        }
        ComboBox<String> races = new ComboBox<>();
        races.getItems().addAll(character.raceNames);
        coreInfoVbox.getChildren().addAll(raceSelection, races, statGeneration, diceRoller, rolledStats, coreStatsVbox, coreStatsButtons);
        //Dice Roller Button
        diceRoller.setOnAction(actionEvent -> rolledStats.setText(DiceRoller.getStats().toString()));
        //Race Selection
        races.setOnAction(actionEvent -> {
            for (Race race : Race.values()) {
                if (races.getValue().equals(race.viewName)) {
                    character.setRace(race);
                }
            }
        });

        //***3rd Scene Combat Style***
        VBox combatStyle = new VBox();
        combatStyle.setAlignment(Pos.CENTER);
        combatStyle.setSpacing(15);
        combatStyle.setBackground(Background.EMPTY);
        Scene combatStyleScene = new Scene(combatStyle, 550, 850, bgColor);
        Label combatStyleLabel = new Label("Select a combat style!");
        Label styleDescription = new Label("");
        Button nextToRacial = new Button("Next (Racial)");
        Button backToCore = new Button("Back (Core and Race Selection)");
        HBox combatStyleButtons = new HBox(backToCore, nextToRacial);
        combatStyleButtons.setAlignment(Pos.CENTER);
        combatStyleButtons.setSpacing(25);
        ToggleGroup styleGroup = new ToggleGroup();
        VBox combatStyleButtonVbox = new VBox();
        combatStyleButtonVbox.setSpacing(15);
        combatStyleButtonVbox.setAlignment(Pos.CENTER);
        for(String style : character.styles){
            RadioButton button = new RadioButton(style);
            button.setUserData(style);
            combatStyleButtonVbox.getChildren().add(button);
            button.setToggleGroup(styleGroup);
        }
        styleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (styleGroup.getSelectedToggle() != null) {
                String style = styleGroup.getSelectedToggle().getUserData().toString();
                styleDescription.setText(character.getStyleDescription(style));
                String fullStyle = style + ":\n" + styleDescription.getText();
                character.setStyle(fullStyle);
            }
        });
        combatStyle.getChildren().addAll(combatStyleLabel, combatStyleButtonVbox, styleDescription, combatStyleButtons);

        //Button nextToSkillsBackground = new Button("Next (Skills and Background"); For iteration 2

        /*4th (Skills and Background)    //THIS IS FOR ITERATION 2
        VBox skillBackground = new VBox();
        Scene skillsBackgroundScene= new Scene(skillBackground, 600,600);
        Label skillBackgroundLabel=new Label("Choose your skills and Background");
        Button backToCombatStyle =new Button("Back (Core Attributes)");
        Button nextToRacial = new Button("Next (To racial");*/


        //***5th Scene Racial Options***
        VBox racialVbox = new VBox();
        racialVbox.setAlignment(Pos.CENTER);
        racialVbox.setSpacing(10);
        racialVbox.setBackground(Background.EMPTY);
        Scene racialScene = new Scene(racialVbox, 550, 850, bgColor);
        Label racialAttributesHeader = new Label("Specific Racial Options");
        //Button backToSkillsBackground = new Button("Back (to Skills and Background)");  Will be added back in iteration 2.
        Button backToCombatStyle = new Button("Back (to Core Attributes)");//Will go away iteration 2.
        Label makeSure = new Label("Make sure you have selected all desired fields, options, and/or boxes. \nIf you left anything blank, related fields in the pdf will be affected!\n For example, failing to enter a Constitution score will affect values for: \n-Fortitude save\n-Constitution Modifier\n-Hit points\nNo one wants a Constition score of 0,1, or 2!");
        Button finish = new Button("Finish");
        HBox racialButtons = new HBox(backToCombatStyle, finish);
        racialButtons.setAlignment(Pos.BOTTOM_CENTER);
        racialButtons.setSpacing(25);

        nextToRacial.setOnAction(Event -> {
            character.racialSceneOptionSetter();
            ImageView racialImageView = new ImageView();
            Image racialImage = new Image(character.imageLocationString);
            racialImageView.setImage(racialImage);
            Label racialLabel= new Label(character.racialLabelString);
            racialVbox.getChildren().addAll(racialAttributesHeader,racialLabel,racialImageView, character.subRaceVbox, makeSure, racialButtons);
            stage.setScene(racialScene);
        });


        //***6th Scene Save Options***
        VBox saveLocationVbox = new VBox();
        saveLocationVbox.setAlignment(Pos.CENTER);
        saveLocationVbox.setSpacing(50);
        saveLocationVbox.setBackground(Background.EMPTY);
        Scene saveScene = new Scene(saveLocationVbox, 550, 850, bgColor);
        Label saveLabel = new Label("Please select a path to save your PDF to.");
        DirectoryChooser saveLocation = new DirectoryChooser();
        saveLocation.setInitialDirectory(new File("src"));
        Button save = new Button("Select Save Location (Program then closes)");
        Button backToRacial = new Button("Back (to Racial)");
        saveLocationVbox.getChildren().addAll(saveLabel, save, backToRacial);
        //Save Button
        save.setOnAction(actionEvent -> {
            File saveFile = new File(saveLocation.showDialog(stage) + "/" + character.getName() + ".pdf");
            PdfGenerator generator = new PdfGenerator.Builder().setCharacter(character).build();
            try {
                generator.writeNewCharacterSheet(saveFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.close();
        });

        //***PAGE TRAVERSAL BUTTONS***
        //GO! button
        go.setOnAction(actionEvent -> {
            character.setName(characterName.getText());
            stage.setScene(coreAttributesScene);
        });

        //Back to Name Button
        backToName.setOnAction(actionEvent -> stage.setScene(openAndNameScene));

        //Next to Combat Style Button
        nextToCombatStyle.setOnAction(actionEvent -> stage.setScene(combatStyleScene));

        //Back to Core
        backToCore.setOnAction(actionEvent -> stage.setScene(coreAttributesScene));

        //Back to Combat Style
        backToCombatStyle.setOnAction(actionEvent -> {
            racialVbox.getChildren().clear();
            stage.setScene(combatStyleScene);
        });

        //Finish Button
        finish.setOnAction(actionEvent -> stage.setScene(saveScene));

        //Back to Racial Button
        backToRacial.setOnAction(actionEvent -> stage.setScene(racialScene));

        //JavaFX End Statements
        stage.setScene(openAndNameScene);
        stage.setTitle("Dungeons and Dragons Fighter Generator");
        stage.show();
    }
}
