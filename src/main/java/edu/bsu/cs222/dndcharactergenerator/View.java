package edu.bsu.cs222.dndcharactergenerator;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class View extends Application {

    public static void main(String[] args) throws IOException {
        launch((args));
    }

    private VBox buildVbox(int spacing, Pos pos, Background background) {
        VBox vbox= new VBox(spacing);
        vbox.setAlignment(pos);
        vbox.setBackground(background);
        return vbox;
    }
    private HBox buildHbox(int spacing, Pos pos){
        HBox hBox = new HBox(spacing);
        hBox.setAlignment(pos);
        return hBox;
    }

    @Override
    public void start(Stage stage) {

        Character character = new Character();
        Handbook book = new Handbook();
        //Scene 1
        VBox nameVbox= buildVbox(20,Pos.CENTER, Background.EMPTY);
        Scene openAndNameScene = new Scene(nameVbox,550,850,Color.NAVAJOWHITE);
        Image fighterImage = new Image("/dwarfImage.png", true);
        ImageView fighterImageView = new ImageView(fighterImage);
        Label enterName = new Label("Enter your name below, bold adventurer!  Then press 'GO!'\n\n" +
                " *Leaving this blank is fine, you can always change it later!");
        TextField characterName= new TextField();
        Button nextToCoreAndRaceSelection = new Button("GO!");
        nameVbox.getChildren().addAll(fighterImageView, enterName, characterName, nextToCoreAndRaceSelection);
        //Scene 2
        VBox coreStatsAndRacialVbox = buildVbox(10,Pos.CENTER, Background.EMPTY);
        VBox coreStatsVbox = buildVbox(10,Pos.CENTER,Background.EMPTY);
        Scene coreAttributesScene = new Scene(coreStatsAndRacialVbox,550,850, Color.NAVAJOWHITE);
        Label raceSelection = new Label("Choose from one of the below races!");
        Label statGeneration = new Label("Fill out your rolled stats below.\n" +
                "We have provided a dice roller that uses standard rules,\n" +
                "Feel free to insert your own values, or take ours!");
        Label rolledStats = new Label("");
        Button diceRoller = new Button("Roll the Dice!");
        Button nextToCombatStyle = new Button("Next (Combat Style)");
        Button backToName = new Button("Back (Name Screen)");
        HBox coreStatsButtonsHbox = buildHbox(25,Pos.CENTER);
        coreStatsButtonsHbox.getChildren().addAll(backToName, nextToCombatStyle);
        for (AbilityScore abilityScore: AbilityScore.values()) {//TODO FIX ABILITYSCORE LOOP BASED UPON INCOMING MODEL CHANGES
            Label abilityLabel = new Label(abilityScore.viewName);
            ComboBox<Integer> abilityInQuestion = new ComboBox();
            abilityInQuestion.getItems().addAll(character.statNumbers);
            coreStatsVbox.getChildren().addAll(abilityLabel,abilityInQuestion);
            abilityInQuestion.setOnAction(e -> character.setAbilityScore(abilityScore, abilityInQuestion.getValue()));
        }
        ComboBox<String> races = new ComboBox<>();
        races.getItems().addAll(book.raceNames);
        coreStatsAndRacialVbox.getChildren().addAll(raceSelection, races, statGeneration, diceRoller, rolledStats, coreStatsVbox, coreStatsButtonsHbox);
        diceRoller.setOnAction(actionEvent -> rolledStats.setText(DiceRoller.getStats().toString()));
        races.setOnAction(actionEvent -> {
                for (Race race : book.getRaces()) {
                    if (races.getValue().equals(race.viewName)) {
                        character.setRace(race);
                    }
                }
        });
        //Scene 3
        VBox combatStyleVbox = buildVbox(15,Pos.CENTER,Background.EMPTY);
        Scene combatStyleScene = new Scene(combatStyleVbox,550,850,Color.NAVAJOWHITE);
        Label combatStyleLabel = new Label("Select a combat style!");
        Label styleDescription = new Label("");
        Button nextToRacial = new Button("Next (Racial)");
        Button backToCore = new Button("Back (Core and Race Selection)");
        HBox combatStyleButtonsHbox = buildHbox(25,Pos.CENTER);
        combatStyleButtonsHbox.getChildren().addAll(backToCore,nextToRacial);
        VBox combatStyleButtonVbox = buildVbox(15,Pos.CENTER,Background.EMPTY);
        ToggleGroup styleGroup = new ToggleGroup();
        for(String style : book.styles){
            RadioButton button = new RadioButton(style);
            button.setUserData(style);
            combatStyleButtonVbox.getChildren().add(button);
            button.setToggleGroup(styleGroup);
        }
        styleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (styleGroup.getSelectedToggle() != null) {
                String style = styleGroup.getSelectedToggle().getUserData().toString();
                styleDescription.setText(book.getStyleDescription(style));
                String fullStyle = style + ":\n" + styleDescription.getText();
                character.setStyle(fullStyle);
            }
        });
        combatStyleVbox.getChildren().addAll(combatStyleLabel, combatStyleButtonVbox, styleDescription, combatStyleButtonsHbox);
        //Scene 4
        VBox racialVbox = buildVbox(10,Pos.CENTER,Background.EMPTY);
        Scene racialScene = new Scene(racialVbox, 550, 850, Color.NAVAJOWHITE);
        Label racialAttributesHeader = new Label("Specific Racial Options");
        Label makeSure = new Label("Make sure you have selected all desired fields, options, and/or boxes. \nIf you left anything blank, related fields in the pdf will be affected!\n For example, failing to enter a Constitution score will affect values for: \n-Fortitude save\n-Constitution Modifier\n-Hit points\nNo one wants a Constition score of 0,1, or 2!");
        Button backToCombatStyle = new Button("Back (to Core Attributes)");
        Button nextToBackground = new Button("Next (to Backgrounds");
        VBox subRaceVbox = buildVbox(20,Pos.CENTER,Background.EMPTY);
        ComboBox<String> subraceComboBox = new ComboBox<>();
        subraceComboBox.setOnAction(actionEvent -> {
            for (Subrace subrace : Subrace.values()) {
                if (subraceComboBox.getValue().equals(subrace.attributeName)) {
                    character.setSubrace(subrace);
                        }
                    }
                });
        HBox racialButtonsHbox = buildHbox(25,Pos.BOTTOM_CENTER);
        racialButtonsHbox.getChildren().addAll(backToCombatStyle, nextToBackground);
        //Scene 5
        VBox backgroundVbox = buildVbox(30,Pos.CENTER,Background.EMPTY);
        Scene backgroundScene= new Scene(backgroundVbox, 550,850,Color.NAVAJOWHITE);
        Label backgroundLabel=new Label("Choose your Background");
        Button backToRacial =new Button("Back (to Racial Options)");
        Button nextToProficiency = new Button("Next (To Fighter Proficiencies");
        ComboBox<String> backgroundComboBox = new ComboBox<>();
        TextArea descriptionFeatures= new TextArea();
        descriptionFeatures.setWrapText(true);
        ScrollPane descriptionFeaturesScrollPane=new ScrollPane();
        for(CharacterBackground background : book.characterBackgroundArray){
        backgroundComboBox.getItems().add(background.getViewName());
        }
        backgroundComboBox.setOnAction(actionEvent ->{
            descriptionFeatures.clear();
            descriptionFeaturesScrollPane.setContent(descriptionFeatures);
            //for( CharacterBackgroundEnum text : CharacterBackgroundEnum.values()) {
            for(CharacterBackground text : book.characterBackgroundArray){
                if (backgroundComboBox.getValue().equals(text.getViewName())){
                    descriptionFeatures.setText("DESCRIPTION\n"+text.getDescription()+"\n\nFEATURES\n"+text.getFeature());
                    character.chosenBackground=text;
                    for (Skill skill : Skill.values()) {
                        if (text.getBgSkill1().equals(skill.viewName)) {
                                character.backgroundSkill1 = skill;
                                System.out.println(skill.viewName);
                            }
                        if (text.getBgSkill2().equals(skill.viewName)) {
                            character.backgroundSkill2 = skill;
                            System.out.println(skill.viewName);
                        }
                    }
                    //character.backgroundSkill1=text.proficiency1;
                    //character.backgroundSkill2=text.proficiency2;
                    Map<Skill, Integer> selectedSkillsMap = new HashMap<>();
                    selectedSkillsMap.clear();
                    selectedSkillsMap.put(character.backgroundSkill1, 2);
                    selectedSkillsMap.put(character.backgroundSkill2, 2);
                    selectedSkillsMap.put(character.fighterSkill1, 2);
                    selectedSkillsMap.put(character.fighterSkill2, 2);
                    }
                }
        });
        HBox skillButtonsHbox = buildHbox(25,Pos.BOTTOM_CENTER);
        skillButtonsHbox.getChildren().addAll(backToRacial,nextToProficiency);
        backgroundVbox.getChildren().addAll(backgroundLabel,backgroundComboBox,descriptionFeaturesScrollPane,skillButtonsHbox);
        stage.setScene(backgroundScene);
        //Scene 6
        VBox outerProficiencyVbox =buildVbox(10,Pos.CENTER,Background.EMPTY);
        VBox innerProficiencyVbox = buildVbox(20,Pos.CENTER,Background.EMPTY);
        Scene fighterProficiencyScene = new Scene(outerProficiencyVbox,550,850,Color.NAVAJOWHITE);
        Label fighterProficiencyLabel = new Label("Choose your two fighter proficiencies!\nWe have removed those you already get from your Background!");
        Button backToBackground= new Button("Back (to Background)");
        Button nextToSave = new Button("Next (to Save Options)");
        HBox proficiencyButtonsHBox=buildHbox(25,Pos.CENTER);
        proficiencyButtonsHBox.getChildren().addAll(backToBackground,nextToSave);
        outerProficiencyVbox.getChildren().addAll(fighterProficiencyLabel,innerProficiencyVbox,proficiencyButtonsHBox);
        stage.setScene(fighterProficiencyScene);
        //Scene 7
        VBox saveLocationVbox = buildVbox(50,Pos.CENTER,Background.EMPTY);
        Scene saveScene = new Scene(saveLocationVbox, 550, 850, Color.NAVAJOWHITE);
        Label saveLabel = new Label("Please select a path to save your PDF to.");
        DirectoryChooser saveLocation = new DirectoryChooser();
        saveLocation.setInitialDirectory(new File("src"));
        Button save = new Button("Select Save Location (Program then closes)");
        Button backToProficiency = new Button("Back (to Skills & Background)");
        saveLocationVbox.getChildren().addAll(saveLabel, save, backToProficiency);
        //Scene 8
        save.setOnAction(actionEvent -> {
            character.buildfinalCharacterStats();
            JFrame parent = new JFrame();
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            } catch (Exception e) {
                e.printStackTrace();
            }
            PdfGenerator generator = new PdfGenerator.Builder().setCharacter(character).build();
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Name your file!");
            int userSelection = fileChooser.showSaveDialog(parent);
            if(userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                try {
                    generator.writeNewCharacterSheet(fileToSave);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            stage.close();
        });

        nextToCoreAndRaceSelection.setOnAction(actionEvent -> {
            character.setName(characterName.getText());
            stage.setScene(coreAttributesScene);
        });
        nextToCombatStyle.setOnAction(actionEvent ->{
                if(races.getSelectionModel().isEmpty()) {
                    character.setRace(Race.ZEROMAN);
                }
                stage.setScene(combatStyleScene);
                });


        nextToRacial.setOnAction(Event -> {
            subraceComboBox.getItems().clear();
            subRaceVbox.getChildren().clear();
            for(Subrace subrace : book.getSubracesOfRace(character.getRace())){
                subraceComboBox.getItems().add(subrace.attributeName);
            }
            Image racialImage = new Image(character.getRace().picture);
            ImageView racialImageView = new ImageView(racialImage);
            Label racialLabel= new Label(character.getRace().label);
            racialVbox.getChildren().addAll(racialAttributesHeader,racialLabel,racialImageView, subraceComboBox, makeSure, racialButtonsHbox);
            stage.setScene(racialScene);
        });


        nextToBackground.setOnAction(actionEvent -> stage.setScene(backgroundScene));
        nextToProficiency.setOnAction(actionEvent -> {
            innerProficiencyVbox.getChildren().clear();
            selectFighterProficiency(innerProficiencyVbox,character.chosenBackground,character);
            stage.setScene(fighterProficiencyScene);
        });
        nextToSave.setOnAction(actionEvent -> stage.setScene(saveScene));

        backToName.setOnAction(actionEvent -> stage.setScene(openAndNameScene));
        backToCore.setOnAction(actionEvent -> stage.setScene(coreAttributesScene));
        backToCombatStyle.setOnAction(actionEvent -> {
            racialVbox.getChildren().clear();
            stage.setScene(combatStyleScene);
        });
        backToRacial.setOnAction(actionEvent -> stage.setScene(racialScene));
        backToBackground.setOnAction(actionEvent -> stage.setScene(backgroundScene));
        backToProficiency.setOnAction(actionEvent -> stage.setScene(fighterProficiencyScene));

        stage.setScene(openAndNameScene);
        stage.setTitle("Dungeons and Dragons Fighter Generator");
        stage.show();
    }

    public void selectFighterProficiency(VBox innerProficiencyVbox, CharacterBackground background,Character character) {
        final int maxBoxCount = 2;
        CheckBox[] fighterBoxes = new CheckBox[Skill.values().length];
        ChangeListener<Boolean> listener0 = new ChangeListener<Boolean>() {
            private int listenedCount = 0;

            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    listenedCount++;
                    if (listenedCount == maxBoxCount) {
                        for (CheckBox selBox : fighterBoxes) {
                            if (!selBox.isSelected()) {
                                selBox.setDisable(true);
                            }
                        }
                    }
                } else {
                    if (listenedCount == maxBoxCount) {
                        for (CheckBox selBox : fighterBoxes) {
                            selBox.setDisable(false);
                        }
                    }
                    listenedCount--;
                }
            }
        };
        int counter = 0;

        for (Skill skill : Skill.values()) {
            CheckBox selbox = new CheckBox(skill.viewName);
            selbox.selectedProperty().addListener(listener0);
            if ((skill.isFighterOption) && (!skill.equals(background.getBgSkill1()) && (!skill.equals(background.getBgSkill2())))) {
                innerProficiencyVbox.getChildren().add(selbox);
                selbox.setOnAction(actionEvent -> {
                    if (selbox.isSelected()) {
                        skillAllocate(skill,character);
                    }
                    Map<Skill, Integer> selectedSkillsMap = new HashMap<>();
                    selectedSkillsMap.clear();
                    selectedSkillsMap.put(character.backgroundSkill1, 2);
                    selectedSkillsMap.put(character.backgroundSkill2, 2);
                    selectedSkillsMap.put(character.fighterSkill1, 2);
                    selectedSkillsMap.put(character.fighterSkill2, 2);
                });
            }
            fighterBoxes[counter] = selbox;
            counter++;
        }
    }

    public void skillAllocate(Skill skill, Character character) {
        int checkCounter = 0;
        if (checkCounter == 0 || (checkCounter > 1 && checkCounter % 2 == 0)) {
            character.fighterSkill1 = skill;
        } else {
            character.fighterSkill2 = skill;
        }
        checkCounter++;
    }
}
