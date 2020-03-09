package edu.bsu.cs222.dndcharactergenerator;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.File;

public class View extends Application {

    public static void main(String[] args) {
        launch((args));
    }

    @Override
    public void start(Stage stage) {
        Character character = new Character();

        //1st Name Scene
        VBox nameVbox = new VBox();
        Image fighterImage = new Image("/dwarfImage.png", true);
        ImageView fighterImageView = new ImageView();
        fighterImageView.setImage(fighterImage);
        Label enterName = new Label("Enter your name below, bold adventurer!  Then press 'GO!'\n\n" +
                " *Leaving this blank is fine, you can always change it later!");
        TextField characterName = new TextField();
        Button go = new Button("GO!");
        Scene openAndNameScene = new Scene(nameVbox, 600, 800);
        nameVbox.getChildren().addAll(fighterImageView, enterName, characterName, go);


        //2nd Core Stats/Race Selection Scene
        VBox coreStatsVbox = new VBox();
        Scene coreAttributesScene = new Scene(coreStatsVbox, 800, 800);
        Label raceSelection = new Label("Choose from one of the below races!");
        Label statGeneration = new Label("Fill out your rolled stats below.\n" +
                "We have provided a dice roller that uses standard rules,\n" +
                "Feel free to insert your own values, or take ours!");
        Button nextToCombatStyle = new Button("Next (Combat Style");
        Button diceRoller = new Button("Roll the Dice!");
        Integer[] statNumbers = new Integer[] {3,4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22};
        Label rolledStats = new Label("");
        Label strLabel = new Label("Strength");
        ComboBox<Integer> strBox = new ComboBox<>();
        strBox.getItems().addAll(statNumbers);
        Label dexLabel = new Label("Dexterity");
        ComboBox<Integer> dexBox = new ComboBox<>();
        dexBox.getItems().addAll(statNumbers);
        Label conLabel = new Label("Constitution");
        ComboBox<Integer> conBox = new ComboBox<Integer>();
        conBox.getItems().addAll(statNumbers);
        Label intLabel = new Label("Intelligence");
        ComboBox<Integer> intBox = new ComboBox<Integer>();
        intBox.getItems().addAll(statNumbers);
        Label wisLabel = new Label("Wisdom");
        ComboBox<Integer> wisBox = new ComboBox<Integer>();
        wisBox.getItems().addAll(statNumbers);
        Label chaLabel = new Label("Charisma");
        ComboBox<Integer> chaBox = new ComboBox<>();
        chaBox.getItems().addAll(statNumbers);
        ComboBox<String> races = new ComboBox<>();
        Button backToName = new Button("Back (Name Screen)");
        races.getItems().addAll("Dragonborn", "Dwarf", "Elf", "Gnome", "Half-Elf", "Halfling", "Half-Orc", "Human", "Tiefling");
        coreStatsVbox.getChildren().addAll(raceSelection, races, statGeneration, diceRoller, rolledStats, strLabel, strBox, dexLabel, dexBox, conLabel, conBox, intLabel, intBox, wisLabel, wisBox, chaLabel, chaBox, nextToCombatStyle, backToName);

        //3rd Scene Combat Style
        VBox combatStyle = new VBox();
        Scene combatStyleScene = new Scene(combatStyle, 800, 800);
        Label combatStyleLabel = new Label("Select a combat style!");
        Label styleDescription = new Label("");
        Button nextToRacial = new Button("Next (Racial)");
        Button backToCore = new Button("Back (Core and Race Selection)");

        ToggleGroup styleGroup = new ToggleGroup();

        RadioButton archery = new RadioButton("Archery");
        archery.setUserData("Archery");
        archery.setToggleGroup(styleGroup);

        RadioButton defense = new RadioButton("Defense");
        defense.setUserData("Defense");
        defense.setToggleGroup(styleGroup);

        RadioButton dueling = new RadioButton("Dueling");
        dueling.setUserData("Dueling");
        dueling.setToggleGroup(styleGroup);

        RadioButton greatWeaponFighting = new RadioButton("Great Weapon Fighting");
        greatWeaponFighting.setUserData("Great Weapon Fighting");
        greatWeaponFighting.setToggleGroup(styleGroup);

        RadioButton protection = new RadioButton("Protection");
        protection.setUserData("Protection");
        protection.setToggleGroup(styleGroup);

        RadioButton twoWeaponFighting = new RadioButton("Two-Weapon Fighting");
        twoWeaponFighting.setUserData("Two-Weapon Fighting");
        twoWeaponFighting.setToggleGroup(styleGroup);

        styleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (styleGroup.getSelectedToggle() != null) {
                String style = styleGroup.getSelectedToggle().getUserData().toString();
                character.setStyleLiteral(style);
                System.out.println(style);

                switch (style) {
                    case "Archery":
                        styleDescription.setText("+2 bonus to attack rolls made with ranged weapons");
                        break;
                    case "Defense":
                        styleDescription.setText("+1 bonus to AC while wearing armor");
                        break;
                    case "Dueling":
                        styleDescription.setText("+2 bonus to damage rolls when holding a single weapon");
                        break;
                    case "Great Weapon Fighting":
                        styleDescription.setText("You may re-roll the damage die while holding a two-handed weapon if you rolled a 1 or a 2");
                        break;
                    case "Protection":
                        styleDescription.setText("When wielding a shield, you may impose a disadvantage " +
                                "on an enemy creature's attack roll when it attacks\na target" +
                                " other than you that is both within 5 feet of you and in your sight");
                        break;
                    case "Two-Weapon Fighting":
                        styleDescription.setText("When you engage in two weapon fighting you can add you ability modifier to the damage of the second attack");
                        break;
                    default:
                        styleDescription.setText("No style found");
                        break;
                }
            }
        });
        combatStyle.getChildren().addAll(combatStyleLabel, archery, defense, dueling, greatWeaponFighting, protection, twoWeaponFighting, styleDescription, backToCore, nextToRacial);

        //Button nextToSkillsBackground = new Button("Next (Skills and Background"); For iteration 2

        /*4th (Skills and Background)    //THIS IS FOR ITERATION 2
        VBox skillBackground = new VBox();
        Scene skillsBackgroundScene= new Scene(skillBackground, 600,600);
        Label skillBackgroundLabel=new Label("Choose your skills and Background");
        Button backToCombatStyle =new Button("Back (Core Attributes)");
        Button nextToRacial = new Button("Next (To racial");*/


        //5th (Racial Scene)
        VBox racialVbox = new VBox();
        Scene racialScene = new Scene(racialVbox, 1000, 1000);
        Label racialAttributesHeader = new Label("Specific Racial Options");
        Label racialInformation = new Label("COLIN PUT STUFF HERE");
        //Button backToSkillsBackground = new Button("Back (to Skills and Background)");  Will be added back in iteration 2.
        Button backToCombatStyle = new Button("Back (to Core Attributes");//Will go away iteration 2.
        Label makeSure = new Label("Make sure you have selected all desired fields, options, and/or boxes. \nIf you left anything blank, related fields in the pdf will be affected!\n For example, failing to enter a Constitution score will \n cause Fortitude save, contitution modifier, and hit points fields to remain empty, or reflect only your racial modifiers!\n No one wants a Constition score of 0,1, or 2!");
        Button finish = new Button("Finish");

        //6th Save Scene
        VBox saveLocationVbox = new VBox();
        Scene saveScene = new Scene(saveLocationVbox, 200, 200);
        Label saveLabel = new Label("Please select a path to save your PDF to.");
        DirectoryChooser saveLocation = new DirectoryChooser();
        saveLocation.setInitialDirectory(new File("src"));
        Button save = new Button("Select Save Location");
        Button backToRacial = new Button("Back (to Racial");
        saveLocationVbox.getChildren().addAll(saveLabel, save, backToRacial);


        //GO! button
        go.setOnAction(actionEvent -> {
            character.setName(characterName.getText());
            stage.setScene(coreAttributesScene);
        });

        //Race Selection
        races.setOnAction(actionEvent -> {
            character.setRace(races.getValue().toString());
        });

        //Back to Name Button
        backToName.setOnAction(actionEvent -> {
            stage.setScene(openAndNameScene);
        });

        //Next to Combat Style Button
        nextToCombatStyle.setOnAction(actionEvent -> {
            stage.setScene(combatStyleScene);
        });
        //Next to Racial Button
        nextToRacial.setOnAction(Event -> {
            ImageView racialImageView = new ImageView();
            Image dwarfImage = new Image("/dwarfImage.png");
            Image gnomeImage = new Image("/gnomeImage.png");
            Image halfElfImage = new Image("/halfElfImage.png");
            Image halfOrcImage = new Image("/halfOrcImage.png");
            racialVbox.getChildren().addAll(racialAttributesHeader);
            if (character.getRace().equals(null)) {
                Label noRaceSelected = new Label("NO RACE SELECTED!  YOU MIGHT WANNA FIX THIS!");
                racialVbox.getChildren().addAll(noRaceSelected);
            }

            if (character.getRace().equals(Race.DRAGONBORN)) {
                ComboBox<String> breathWeaponSelection = new ComboBox<>();
                breathWeaponSelection.getItems().addAll("Black Dragon: Acid", "Blue Dragon: Lightning", "Brass Dragon: Fire", "Bronze Dragon: Lightning", "Copper Dragon: Acid",
                        "Gold Dragon: Fire", "Green Dragon: Poison", "Red Dragon: Fire", "Silver Dragon: Cold", "White Dragon: Cold");
                racialVbox.getChildren().addAll(breathWeaponSelection);
                breathWeaponSelection.setOnAction(actionEvent -> {
                    character.setRacialAttribute(stringToRacialAttribute(breathWeaponSelection.getValue().toString()));
                });
            }
            if (character.getRace().equals(Race.DWARF)) {
                racialImageView.setImage(dwarfImage);
                ComboBox<String> dwarfSubRace = new ComboBox<>();
                dwarfSubRace.getItems().addAll("Hill Dwarf: +1 WIS", "Mountain Dwarf: +2 STR");
                racialVbox.getChildren().addAll(dwarfSubRace);
                dwarfSubRace.setOnAction(actionEvent -> {
                    character.setRacialAttribute(stringToRacialAttribute(dwarfSubRace.getValue().toString()));
                });

            }
            if (character.getRace().equals(Race.ELF)) {
                ComboBox<String> elfSubRace = new ComboBox<>();
                elfSubRace.getItems().addAll("High Elf: +1 INT", "Wood Elf: +1 WIS", "Drow: +1 CHA");
                racialVbox.getChildren().addAll(elfSubRace);
                elfSubRace.setOnAction(actionEvent -> {
                    character.setRacialAttribute(stringToRacialAttribute(elfSubRace.getValue().toString()));
                });

            }
            if (character.getRace().equals(Race.GNOME)) {
                racialImageView.setImage(gnomeImage);
                ComboBox<String> gnomeSubRace = new ComboBox<String>();
                gnomeSubRace.getItems().addAll("Forest Gnome: +1 DEX", "Rock Gnome: +1 CON");
                racialVbox.getChildren().addAll(gnomeSubRace);
                gnomeSubRace.setOnAction(actionEvent -> {
                    character.setRacialAttribute(stringToRacialAttribute(gnomeSubRace.getValue().toString()));
                });

            }
            if (character.getRace().equals(Race.HALFELF)) {
                racialImageView.setImage(halfElfImage);
                Label halfElfRacialAbilityBonus = new Label("Choose which two ability scores to increase by 1");
                String[] statsForBoxes = new String[]{"STR", "DEX", "CON", "INT", "WIS", "CHA"};
                final int maxBoxCount = 2;
                final CheckBox[] halfElfCheckboxes = new CheckBox[statsForBoxes.length];
                ChangeListener<Boolean> listener = new ChangeListener<Boolean>() {
                    private int listenedCount = 0;

                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        if (newValue) {
                            listenedCount++;
                            if (listenedCount == maxBoxCount) {
                                for (CheckBox selBox : halfElfCheckboxes) {
                                    if (!selBox.isSelected()) {
                                        selBox.setDisable(true);
                                    }
                                }
                            }

                        } else {
                            if (listenedCount == maxBoxCount) {
                                for (CheckBox selBox : halfElfCheckboxes) {
                                    selBox.setDisable(false);
                                }
                            }
                            listenedCount--;
                        }
                    }
                };
                racialVbox.getChildren().addAll(halfElfRacialAbilityBonus);
                for (int i = 0; i < statsForBoxes.length; i++) {
                    CheckBox selBox = new CheckBox(statsForBoxes[i]);
                    selBox.selectedProperty().addListener(listener);
                    racialVbox.getChildren().add(selBox);
                    halfElfCheckboxes[i] = selBox;
                }
            }
            if (character.getRace().equals(Race.HALFLING)) {
                ComboBox<String> halflingSubRace = new ComboBox<>();
                halflingSubRace.getItems().addAll("Lightfoot: +1 CHA", "Stout: +1 CON");
                racialVbox.getChildren().addAll(halflingSubRace);
                halflingSubRace.setOnAction(actionEvent -> {
                    character.setRacialAttribute(stringToRacialAttribute(halflingSubRace.getValue().toString()));
                });
            }
            if (character.getRace().equals(Race.HALFORC)) {
                racialImageView.setImage(halfOrcImage);
                Label noSelectionForOrc = new Label("You're a mighty half-orc, you smash good! \nAlso you don't need to wrack that surely massive brain to select further details!");
                racialVbox.getChildren().addAll(noSelectionForOrc);
            }
            if (character.getRace().equals(Race.HUMAN)) {
                Label noSelectionForHuman = new Label("You are a human.  Congratulations.");
                racialVbox.getChildren().addAll(noSelectionForHuman);
            }
            if (character.getRace().equals(Race.TIEFLING)) {
                Label noSelectionForTiefling = new Label("You are a tiefling; try not to burn yourself or others!");
                racialVbox.getChildren().addAll(noSelectionForTiefling);
            }

            racialVbox.getChildren().addAll(racialImageView, racialInformation, makeSure, backToCombatStyle, finish);
            stage.setScene(racialScene);
        });

        //Back to Core
        backToCore.setOnAction(actionEvent -> {
            stage.setScene(coreAttributesScene);
        });
        //Back to Combat Style
        backToCombatStyle.setOnAction(actionEvent -> {
            racialVbox.getChildren().clear();
            stage.setScene(combatStyleScene);
        });

        //Dice Roller Button
        diceRoller.setOnAction(actionEvent -> {
            rolledStats.setText(character.statRoll().toString());
        });

        //Stat Buttons
        strBox.setOnAction(actionEvent -> {
            character.setSTR(strBox.getValue());
            //character.addRacialAbilityScoreBonusIfNotNull();
        });
        dexBox.setOnAction(actionEvent -> {
            character.setDEX(dexBox.getValue());
            //character.addRacialAbilityScoreBonusIfNotNull();
        });
        conBox.setOnAction(actionEvent -> {
            character.setCON(conBox.getValue());
            //character.addRacialAbilityScoreBonusIfNotNull();
        });
        intBox.setOnAction(actionEvent -> {
            character.setINT(intBox.getValue());
            //character.addRacialAbilityScoreBonusIfNotNull();
        });
        wisBox.setOnAction(actionEvent -> {
            character.setWIS(wisBox.getValue());
            //character.addRacialAbilityScoreBonusIfNotNull();
        });
        chaBox.setOnAction(actionEvent -> {
            character.setCHA(chaBox.getValue());
            //character.addRacialAbilityScoreBonusIfNotNull();
        });

        //Finish Button
        finish.setOnAction(actionEvent -> {
            stage.setScene(saveScene);
        });
        //Save Button
        save.setOnAction(actionEvent -> {
            File saveFile = saveLocation.showDialog(stage);
            String filePath = saveFile.getAbsolutePath();
            System.out.println(filePath);
        });
        //Back to Racial Button
        backToRacial.setOnAction(actionEvent -> {
            saveLocationVbox.getChildren().clear();
            stage.setScene(racialScene);
        });

        stage.setScene(openAndNameScene);
        stage.setTitle("Dungeons and Dragons Fighter Generator");
        stage.show();

    }

    public static RacialAttribute stringToRacialAttribute(String racialAttribute) {
        RacialAttribute newAttribute = null;
        switch (racialAttribute) {
            case "Hill Dwarf: +1 WIS":
                newAttribute = RacialAttribute.HILLDWARF;
                break;
            case "Mountain Dwarf: +2 STR":
                newAttribute = RacialAttribute.MOUNTAINDWARF;
                break;
            case "High Elf: +1 INT":
                newAttribute = RacialAttribute.HIGHELF;
                break;
            case "Wood Elf: +1 WIS":
                newAttribute = RacialAttribute.WOODELF;
                break;
            case "Drow: +1 CHA":
                newAttribute = RacialAttribute.DROW;
                break;
            case "Forest Gnome: +1 DEX":
                newAttribute = RacialAttribute.FORESTGNOME;
                break;
            case "Rock Gnome: +1 CON":
                newAttribute = RacialAttribute.ROCKGNOME;
                break;
            case "Lightfoot: +1 CHA":
                newAttribute = RacialAttribute.LIGHTFOOT;
                break;
            case "Stout: +1 CON":
                newAttribute = RacialAttribute.STOUT;
                break;
        }
        return newAttribute;
    }
}
