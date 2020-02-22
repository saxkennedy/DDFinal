package edu.bsu.cs222.dndcharactergenerator;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.EventHandler;

public class View extends Application {

    private String pubRace;

    public static void main(String[] args) {
        launch((args));
    }

    @Override
    public void start(Stage stage) {
        Character character = new Character();

        //First Scene
        VBox vbox0 = new VBox();
        Label enterName = new Label("Enter your name below, bold adventurer!  Then press 'GO!'\n\n" +
                " *Leaving this blank is fine, you can always change it later!");
        TextField characterName = new TextField();
        Button go = new Button("GO!");
        Scene scene0 = new Scene(vbox0, 600, 600);
        vbox0.getChildren().addAll(enterName, characterName, go);


        //Second Scene
        VBox vbox1 = new VBox();
        Scene scene1 = new Scene(vbox1, 600, 600);
        Label raceSelection = new Label("Choose from one of the below races!");
        Label statGeneration = new Label("Fill out your rolled stats below.\n" +
                "We have provided a dice roller that uses standard rules,\n" +
                "Feel free to insert your own values, or take ours!");
        Button nextToSkillsBackground = new Button("Next (to Skills and Background");
        Button diceRoller = new Button("Roll the Dice!");

        Integer[] statNums = new Integer[]{3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22};
        Label rolledStats = new Label("");
        Label strLabel = new Label("Strength");
        ComboBox strBox = new ComboBox();
        strBox.getItems().addAll(statNums);
        Label dexLabel = new Label("Dexterity");
        ComboBox dexBox = new ComboBox();
        dexBox.getItems().addAll(statNums);
        Label conLabel = new Label("Constitution");
        ComboBox conBox = new ComboBox();
        conBox.getItems().addAll(statNums);
        Label intLabel = new Label("Intelligence");
        ComboBox intBox = new ComboBox();
        intBox.getItems().addAll(statNums);
        Label wisLabel = new Label("Wisdom");
        ComboBox wisBox = new ComboBox();
        wisBox.getItems().addAll(statNums);
        Label chaLabel = new Label("Charisma");
        ComboBox chaBox = new ComboBox();
        chaBox.getItems().addAll(statNums);


        ComboBox races = new ComboBox();
        Button back = new Button("Back (Name Screen)");
        races.getItems().addAll("Dragonborn", "Dwarf", "Elf", "Gnome", "Half-Elf", "Halfling", "Half-Orc", "Human", "Tiefling");
        vbox1.getChildren().addAll(raceSelection, races, statGeneration, diceRoller, rolledStats, strLabel, strBox, dexLabel, dexBox, conLabel, conBox, intLabel, intBox, wisLabel, wisBox, chaLabel, chaBox, nextToSkillsBackground, back);

        //3rd (Skills and Background)
        VBox skillBackground = new VBox();
        Scene skillsAndBackground= new Scene(skillBackground, 600,600);
        Label skillBackgroundLabel=new Label("Choose your skills and Background");
        Button nextToRacial = new Button("Next (To racial");
        Button backToCore =new Button("Back (to Core Attributes");

        //4th (Racial Scene)
        VBox racialVbox = new VBox();
        Scene racialScene = new Scene(racialVbox, 600, 600);
        Label racialAttributes = new Label("Specific Racial Options");
        Button backToSkillsBackground = new Button("Back (to Skills and Background)");


        //GO! button
        go.setOnAction(actionEvent -> {
            character.setName(characterName.getText());
            stage.setScene(scene1);
        });

        //Race Selection

        races.setOnAction(actionEvent -> {
            character.setRace(races.getValue().toString());
            pubRace = races.getValue().toString();
        });

        //Back to Name Button
        back.setOnAction(actionEvent -> {
            stage.setScene(scene0);
        });
        //Next to Racial Button
        nextToRacial.setOnAction(actionEvent -> {
            if (character.getRace().equals(Race.DRAGONBORN)) {
                ComboBox breathWeaponSelection = new ComboBox();
                breathWeaponSelection.getItems().addAll("Black Dragon: Acid", "Blue Dragon: Lightning", "Brass Dragon: Fire", "Bronze Dragon: Lightning", "Copper Dragon: Acid",
                        "Gold Dragon: Fire", "Green Dragon: Poison", "Red Dragon: Fire", "Silver Dragon: Cold", "White Dragon: Cold");
                racialVbox.getChildren().addAll(racialAttributes, breathWeaponSelection, backToSkillsBackground);
            }
            if (character.getRace().equals(Race.DWARF)){}
            if (character.getRace().equals(Race.HALFELF)){
                Label halfElfRacialAbilityBonus = new Label("Choose which two ability scores to increase by 1");
                String[] statsForBoxes = new String[]{"STR","DEX","CON","INT","WIS","CHA"};
                final int maxBoxCount=2;
                final CheckBox[] halfElfCheckboxes = new CheckBox[statsForBoxes.length];
                ChangeListener<Boolean> listener = new ChangeListener<Boolean>() {
                    private int listenedCount=0;

                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        if (newValue){
                            listenedCount++;
                            if(listenedCount == maxBoxCount) {
                                for(CheckBox selBox : halfElfCheckboxes){
                                    if (!selBox.isSelected()) {
                                        selBox.setDisable(true);
                                    }
                                }
                            }
                        }
                        else {
                            if (listenedCount == maxBoxCount){
                                for (CheckBox selBox : halfElfCheckboxes){
                                    selBox.setDisable(false);
                                }
                            }
                            listenedCount--;
                        }
                    }
                };
                racialVbox.getChildren().addAll(racialAttributes,halfElfRacialAbilityBonus);
                for(int i =0; i< statsForBoxes.length;i++){
                    CheckBox selBox = new CheckBox(statsForBoxes[i]);
                    selBox.selectedProperty().addListener(listener);
                    racialVbox.getChildren().add(selBox);
                    halfElfCheckboxes[i]=selBox;
                }
            }

            stage.setScene(racialScene);


        });
        //Back to Core Attributes
        backToSkillsBackground.setOnAction(actionEvent -> {
            racialVbox.getChildren().clear();
            stage.setScene(scene1);
        });

        //Dice Roller Button
        diceRoller.setOnAction(actionEvent -> {
            rolledStats.setText(character.statRoll().toString());
        });

        //Stat Buttons
        strBox.setOnAction(actionEvent -> {
            character.setSTR((int) strBox.getValue());
            character.addRacialAbilityScoreBonus();
        });
        dexBox.setOnAction(actionEvent -> {
            character.setDEX((int) dexBox.getValue());
            character.addRacialAbilityScoreBonus();
        });
        conBox.setOnAction(actionEvent -> {
            character.setCON((int) conBox.getValue());
            character.addRacialAbilityScoreBonus();
        });
        intBox.setOnAction(actionEvent -> {
            character.setINT((int) intBox.getValue());
            character.addRacialAbilityScoreBonus();
        });
        wisBox.setOnAction(actionEvent -> {
            character.setWIS((int) wisBox.getValue());
            character.addRacialAbilityScoreBonus();
        });
        chaBox.setOnAction(actionEvent -> {
            character.setCHA((int) chaBox.getValue());
            character.addRacialAbilityScoreBonus();
        });


        stage.setScene(scene0);
        stage.setTitle("Dungeons and Dragons Fighter Generator");
        stage.show();

    }

}
