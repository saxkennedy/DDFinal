package edu.bsu.cs222.dndcharactergenerator;
import javafx.collections.FXCollections;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;
import org.graalvm.compiler.phases.common.NodeCounterPhase;

import java.sql.Array;
import java.util.List;

public class View extends Application {

    public static void main(String[] args) {launch((args));}
    @Override
    public void start(Stage stage){
        Character character = new Character();

        //First Scene
        VBox vbox0 = new VBox();
        Label enterName = new Label("Enter your name below, bold adventurer!  Then press 'GO!'\n\n *Leaving this blank is fine, you can always change it later!");
        TextField characterName = new TextField();
        Button go = new Button("GO!");
        Scene scene0=new Scene(vbox0,400,400);
        vbox0.getChildren().addAll(enterName,characterName,go);


        //Second Scene
        VBox vbox1= new VBox();
        Scene scene1=new Scene(vbox1, 400,400);
        Label raceSelection=new Label("Choose from one of the below races!");
        Label statGeneration = new Label("Fill out your rolled stats below.\nWe have provided a dice roller that uses standard rules,\nFeel free to insert your own values, or take ours!");

        Button diceRoller=new Button("Roll the Dice!");

        Integer[] statNums = new Integer[]{3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22};
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
        vbox1.getChildren().addAll(raceSelection,races,statGeneration,strLabel,strBox,dexLabel,dexBox,conLabel,conBox,intLabel,intBox,wisLabel,wisBox,chaLabel,chaBox,back);


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

        //Dice Roller Button
        diceRoller.setOnAction(actionEvent->{
            //Populate dice rolls.  This is where I stopped 2/18/2020 Joshua Kennedy
        });




        stage.setScene(scene0);
        stage.setTitle("Dungeons and Dragons Fighter Generator");
        stage.show();

    }

}
