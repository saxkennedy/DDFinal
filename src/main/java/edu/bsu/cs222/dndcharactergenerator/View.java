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
        character.chosenBackground=null;

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
        for (AbilityScore abilityScore: AbilityScore.values()) {
            Label abilityLabel = new Label(abilityScore.viewName);
            ComboBox<Integer> abilityInQuestion = new ComboBox();
            abilityInQuestion.getItems().addAll(character.statNumbers);
            coreStatsVbox.getChildren().addAll(abilityLabel,abilityInQuestion);
            abilityInQuestion.setOnAction(e -> {
                character.setAbilityScore(abilityScore, abilityInQuestion.getValue());
                System.out.println("Set Character Attribute");
                System.out.printf("STR: %s",character.getCharacterAttributes());
                System.out.println();
            });
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
                    System.out.println("Set Race");
                    System.out.printf("STR: %s",character.getCharacterAttributes());
                    System.out.println();
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

         //***4th Scene Racial Options***
        VBox racialVbox = new VBox();
        racialVbox.setAlignment(Pos.CENTER);
        racialVbox.setSpacing(10);
        racialVbox.setBackground(Background.EMPTY);
        Scene racialScene = new Scene(racialVbox, 550, 850, bgColor);
        Label racialAttributesHeader = new Label("Specific Racial Options");
        Button backToCombatStyle = new Button("Back (to Core Attributes)");
        Label makeSure = new Label("Make sure you have selected all desired fields, options, and/or boxes. \nIf you left anything blank, related fields in the pdf will be affected!\n For example, failing to enter a Constitution score will affect values for: \n-Fortitude save\n-Constitution Modifier\n-Hit points\nNo one wants a Constition score of 0,1, or 2!");
        Button nextToBackground = new Button("Next (to Backgrounds");
        HBox racialButtons = new HBox(backToCombatStyle, nextToBackground);
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

        //***5th Scene Background****
        VBox backgroundVbox = new VBox();
        backgroundVbox.setAlignment(Pos.CENTER);
        backgroundVbox.setSpacing(30);
        backgroundVbox.setBackground(Background.EMPTY);
        Scene backgroundScene= new Scene(backgroundVbox, 550,850,bgColor);
        Label backgroundLabel=new Label("Choose your Background");
        Button backToRacial =new Button("Back (to Racial Options)");
        Button nextToFighterProficiency = new Button("Next (To Fighter Proficiencies");
        ComboBox<String> backgroundComboBox = new ComboBox<>();
        TextArea descriptionFeatures= new TextArea();
        descriptionFeatures.setWrapText(true);
        ScrollPane descriptionFeaturesScrollPane=new ScrollPane();
        for(CharacterBackground viewName : CharacterBackground.values()){
            backgroundComboBox.getItems().add(viewName.viewName);
        }
        backgroundComboBox.setOnAction(actionEvent ->{
            descriptionFeatures.clear();
            descriptionFeaturesScrollPane.setContent(descriptionFeatures);
            for( CharacterBackground text : CharacterBackground.values()) {
                if (backgroundComboBox.getValue().equals(text.viewName)){
                    descriptionFeatures.setText("DESCRIPTION\n"+text.description+"\n\nFEATURES\n"+text.feature);
                    character.chosenBackground=text;
                    character.backgroundSkill1=text.proficiency1;
                    character.backgroundSkill2=text.proficiency2;
                    character.setProficiencySkillsMap();
                    }
                }
        });
        HBox skillsButtons = new HBox(backToRacial,nextToFighterProficiency);
        skillsButtons.setAlignment(Pos.BOTTOM_CENTER);
        skillsButtons.setSpacing(25);
        backgroundVbox.getChildren().addAll(backgroundLabel,backgroundComboBox,descriptionFeaturesScrollPane,skillsButtons);
        stage.setScene(backgroundScene);

        //***6th Scene Fighter Proficiencies***
        VBox fighterProficiencyVBox =new VBox();
        fighterProficiencyVBox.setAlignment(Pos.CENTER);
        fighterProficiencyVBox.setSpacing(10);
        fighterProficiencyVBox.setBackground(Background.EMPTY);
        VBox innerProficiencyVbox = new VBox();
        innerProficiencyVbox.setAlignment(Pos.CENTER);
        innerProficiencyVbox.setSpacing(20);
        innerProficiencyVbox.setBackground(Background.EMPTY);

        Scene fighterProficiencyScene = new Scene(fighterProficiencyVBox,550,850,bgColor);
        Label fighterProficiencyLabel = new Label("Choose your two fighter proficiencies!\nWe have removed those you already get from your Background!");
        Button backToBackground= new Button("Back (to Background)");
        Button nextToSave = new Button("Next (to Save Options)");
        HBox fighterProficiencyButtons=new HBox(backToBackground,nextToSave);
        fighterProficiencyButtons.setAlignment(Pos.BOTTOM_CENTER);
        fighterProficiencyButtons.setSpacing(25);



        fighterProficiencyVBox.getChildren().addAll(fighterProficiencyLabel,innerProficiencyVbox,fighterProficiencyButtons);
        stage.setScene(fighterProficiencyScene);

        //***7th Scene Save Options***
        VBox saveLocationVbox = new VBox();
        saveLocationVbox.setAlignment(Pos.CENTER);
        saveLocationVbox.setSpacing(50);
        saveLocationVbox.setBackground(Background.EMPTY);
        Scene saveScene = new Scene(saveLocationVbox, 550, 850, bgColor);
        Label saveLabel = new Label("Please select a path to save your PDF to.");
        DirectoryChooser saveLocation = new DirectoryChooser();
        saveLocation.setInitialDirectory(new File("src"));
        Button save = new Button("Select Save Location (Program then closes)");
        Button backToFighterProficiency = new Button("Back (to Skills & Background)");
        saveLocationVbox.getChildren().addAll(saveLabel, save, backToFighterProficiency);

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

        //Next to Background Button
        nextToBackground.setOnAction(actionEvent ->{
            stage.setScene(backgroundScene);
        });

        //Back to Background Button
        backToBackground.setOnAction(actionEvent ->{
            stage.setScene(backgroundScene);
        });

        //Back to Racial Button
        backToRacial.setOnAction(actionEvent -> stage.setScene(racialScene));
        //Next to Fighter Proficiency
        nextToFighterProficiency.setOnAction(actionEvent -> {
            innerProficiencyVbox.getChildren().clear();
            character.selectFighterProficiency(innerProficiencyVbox,character.chosenBackground);
            stage.setScene(fighterProficiencyScene);
        });

        //Back to Fighter Proficiency
        backToFighterProficiency.setOnAction(actionEvent ->{
            stage.setScene(fighterProficiencyScene);
        });

        //Next to Save Button
        nextToSave.setOnAction(actionEvent ->{
                System.out.println(character.selectedSkillsMap);
                stage.setScene(saveScene);
        });

        //JavaFX End Statements
        stage.setScene(openAndNameScene);
        stage.setTitle("Dungeons and Dragons Fighter Generator");
        stage.show();
    }
}
