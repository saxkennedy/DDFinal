package edu.bsu.cs222.dndcharactergenerator;
import javafx.application.Application;
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

    public static void main(String[] args) {launch((args));}
    @Override
    public void start(Stage stage) {
        Character character = new Character();

        //First Scene
        VBox vbox0 = new VBox();
        Label enterName = new Label("Enter your name below, bold adventurer!  Then press 'GO!'\n\n *Leaving this blank is fine, you can always change it later!");
        TextField characterName = new TextField();
        Button go = new Button("GO!");
        Scene scene0=new Scene(vbox0,600,600);
        vbox0.getChildren().addAll(enterName,characterName,go);


        //Second Scene
        VBox vbox1= new VBox();
        Scene scene1=new Scene(vbox1, 600,600);
        Label raceSelection=new Label("Choose from one of the below races!");
        Label statGeneration = new Label("Fill out your rolled stats below.\nWe have provided a dice roller that uses standard rules,\nFeel free to insert your own values, or take ours!");
        Button nextToRacial =new Button("Next");
        Button diceRoller=new Button("Roll the Dice!");

        Integer[] statNums = new Integer[]{3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22};
        Label rolledStats=new Label("");
        Label strLabel=new Label("Strength");
        ComboBox strBox=new ComboBox();
        strBox.getItems().addAll(statNums);
        Label dexLabel=new Label("Dexterity");
        ComboBox dexBox=new ComboBox();
        dexBox.getItems().addAll(statNums);
        Label conLabel=new Label("Constitution");
        ComboBox conBox=new ComboBox();
        conBox.getItems().addAll(statNums);
        Label intLabel=new Label("Intelligence");
        ComboBox intBox=new ComboBox();
        intBox.getItems().addAll(statNums);
        Label wisLabel=new Label("Wisdom");
        ComboBox wisBox=new ComboBox();
        wisBox.getItems().addAll(statNums);
        Label chaLabel=new Label("Charisma");
        ComboBox chaBox=new ComboBox();
        chaBox.getItems().addAll(statNums);


        ComboBox races = new ComboBox();
        Button back = new Button("Back (Name Screen)");
        races.getItems().addAll("Dragonborn","Dwarf","Elf","Gnome","Half-Elf","Halfling","Half-Orc","Human","Tiefling");
        vbox1.getChildren().addAll(raceSelection,races,statGeneration,diceRoller,rolledStats,strLabel,strBox,dexLabel,dexBox,conLabel,conBox,intLabel,intBox,wisLabel,wisBox,chaLabel,chaBox,nextToRacial,back);

        //Racial Scene
        VBox racialVbox= new VBox();
        Scene racialScene=new Scene(racialVbox, 600,600);
        Group racialGroup=new Group();
        Label racialAttributes=new Label(character.getRace()+" Racial Options");
        Button back2=new Button("Back (Core Attributes)");
        racialVbox.getChildren().addAll(racialAttributes,racialGroup,back2);

        //Dragonborn Racial Group
        ComboBox breathWeaponSelection = new ComboBox();
        breathWeaponSelection.getItems().addAll("Black Dragon: Acid","Blue Dragon: Lightning","Brass Dragon: Fire","Bronze Dragon: Lightning","Copper Dragon: Acid",
                "Gold Dragon: Fire","Green Dragon: Poison","Red Dragon: Fire","Silver Dragon: Cold","White Dragon: Cold");

        //Half-Elf Racial Group
        Label halfElfRacialAbilityBonus= new Label("Choose which two ability scores to increase by 1");
        Group halfElfCheckBoxGroup=new Group();
        CheckBox strCheckBox = new CheckBox("STR");
        CheckBox dexCheckBox=new CheckBox("DEX");
        CheckBox conCheckBox= new CheckBox("CON");
        CheckBox intCheckBox=new CheckBox("INT");
        CheckBox wisCheckBox = new CheckBox("WIS");
        CheckBox chaCheckBox = new CheckBox("CHA");

        CheckBox[]halfElfCheckboxes={strCheckBox,dexCheckBox,conCheckBox,intCheckBox,wisCheckBox,chaCheckBox};
        for (int i=0; i<halfElfCheckboxes.length;i++) {
            halfElfCheckboxes[i].selectedProperty().addListener((x,oldS,newS) ->
            {
                if(newS){
                    int selected=0;
                    for(CheckBox option : halfElfCheckboxes) {
                        if (option.isSelected())
                            selected++;
                    }
                    x.equals(selected <= 2);
                }
            });
        }
        halfElfCheckBoxGroup.getChildren().addAll(strCheckBox,dexCheckBox,conCheckBox,intCheckBox,wisCheckBox,chaCheckBox);


        //GO! button
        go.setOnAction(actionEvent-> {
                    character.setName(characterName.getText());
                    stage.setScene(scene1);
        });

        //Race Selection
        races.setOnAction(actionEvent->{
            character.setRace(races.getValue().toString());
        });

        //Back to Name Button
        back.setOnAction(actionEvent->{
            stage.setScene(scene0);
        });
        //Next to Racial Button
        nextToRacial.setOnAction(actionEvent->{
            if (character.getRace().equals(Race.DRAGONBORN))
                racialGroup.getChildren().add(breathWeaponSelection);

            if (character.getRace().equals(Race.HALFELF))
                racialGroup.getChildren().addAll(halfElfRacialAbilityBonus,halfElfCheckBoxGroup);
            stage.setScene(racialScene);
        });
        //Back to Core Attributes
        back2.setOnAction(actionEvent->{
            racialGroup.getChildren().clear();
            stage.setScene(scene1);
        });

        //Dice Roller Button
        diceRoller.setOnAction(actionEvent->{
            rolledStats.setText(character.statRoll().toString());
        });

        //Stat Buttons
        strBox.setOnAction(actionEvent->{
            character.setSTR((int)strBox.getValue());
        });
        dexBox.setOnAction(actionEvent->{
            character.setDEX((int)dexBox.getValue());
        });
        conBox.setOnAction(actionEvent->{
            character.setCON((int)conBox.getValue());
        });
        intBox.setOnAction(actionEvent->{
            character.setINT((int)intBox.getValue());
        });
        wisBox.setOnAction(actionEvent->{
            character.setWIS((int)wisBox.getValue());
        });
        chaBox.setOnAction(actionEvent->{
            character.setCHA((int)chaBox.getValue());
        });






        stage.setScene(scene0);
        stage.setTitle("Dungeons and Dragons Fighter Generator");
        stage.show();

    }

}
