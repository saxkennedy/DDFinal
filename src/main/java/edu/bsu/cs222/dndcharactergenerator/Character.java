package edu.bsu.cs222.dndcharactergenerator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

public class Character {
    private String name;
    private String style;
    private Race race;
    private RacialAttribute racialAttribute;
    private Map<CharacterAttribute, Integer> attributeMap = new HashMap<>();

    Integer[] statNumbers = new Integer[]{3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22};
    String[] raceNames = new String[]{"Half-Orc: +2 STR, +1 CON", "Dragonborn: +2 STR, +1 CHA", "Dwarf: +2 CON", "Elf: +2 DEX", "Gnome: +2 INT", "Half-Elf: +2 CHA", "Halfling: +2 DEX", "Human: +1 TO ALL STATS", "Tiefling: +2 CHA, +1 INT"};
    String[] styles = new String[]{"Archery", "Defense", "Dueling", "Great Weapon Fighting", "Protection", "Two-Weapon Fighting",};
    Skill backgroundSkill1;
    Skill backgroundSkill2;
    Skill fighterSkill1;
    Skill fighterSkill2;
    int checkCounter = 0;
    public int proficiencyViaFighterLevel = 2;
    public String racialUniqueAttribute="";

    public Map<CharacterAttribute, Integer> getCharacterAttributes() {
        return attributeMap;
    }

    public Map<Skill, Integer> skillMap = new HashMap<>();

    public Map<Skill, Integer> selectedSkillsMap = new HashMap<>();
    public CharacterBackground chosenBackground;

    private void zeroOutStatsIfEmpty() {
        if (attributeMap.isEmpty()) {
            populateAttributesWithZero();
        }
    }

    public void setAbilityScore(AbilityScore abilityScore, int value) {
        zeroOutStatsIfEmpty();
        if (race != null) {
            implementAbilityScoreAffector(this.race, -1);
            attributeMap.put(abilityScore, value);
            implementAbilityScoreAffector(this.race, 1);
        } else {
            attributeMap.put(abilityScore, value);
        }
        setAbilityScoreModifier(abilityScore, value);
    }


    private void setAbilityScoreModifier(AbilityScore abilityScore, int mainAbilityScoreValue) {
        AbilityScoreModifier modifier = abilityScore.modifier;
        int modifierValue = modifierCalculation(mainAbilityScoreValue);
        attributeMap.put(modifier, modifierValue);
        runModifierTasks(modifier, modifierValue);
    }

    private void runModifierTasks(AbilityScoreModifier modifier, int modifierValue) {
        setSkills(modifier, modifierValue);
        switch (modifier) {
            case CON_MOD:
                updateHpValues(modifierValue);
                break;
            case DEX_MOD:
                updateAC(modifierValue);
                break;
        }
    }

    public void checkBoxFlipper(Skill skill) {
        if (checkCounter == 0 || (checkCounter > 1 && checkCounter % 2 == 0)) {
            fighterSkill1 = skill;
        } else {
            fighterSkill2 = skill;
        }
        checkCounter++;
    }

    public void setProficiencySkillsMap() {
        selectedSkillsMap.clear();
        selectedSkillsMap.put(backgroundSkill1, 2);
        selectedSkillsMap.put(backgroundSkill2, 2);
        selectedSkillsMap.put(fighterSkill1, 2);
        selectedSkillsMap.put(fighterSkill2, 2);
    }

    private void setSkills(AbilityScoreModifier modifier, int modifierValue) {
        for (Skill skill : Skill.values()) {
            if (skill.abilityScoreModifier.equals(modifier)) {
                skillMap.put(skill, modifierValue);
            }
        }
    }

    private void updateAC(int modifierValue) {
        attributeMap.put(VitalityModifier.ARMOR_CLASS, modifierValue + 10);
    }

    private void updateHpValues(int conModValue) {
        int lvl1Value = conModValue + 10;
        attributeMap.put(VitalityModifier.MAX_HP, lvl1Value);
        attributeMap.put(VitalityModifier.CURRENT_HP, lvl1Value);
        attributeMap.put(VitalityModifier.TOTAL_HP, lvl1Value);
    }

    private void populateAttributesWithZero() {
        for (CharacterAttribute abilityScore : AbilityScore.values()) {
            attributeMap.put(abilityScore, 0);
        }
        for (CharacterAttribute abilityScoreModifier : AbilityScoreModifier.values()) {
            attributeMap.put(abilityScoreModifier, 0);
        }
        for (CharacterAttribute vitalityModifier : VitalityModifier.values()) {
            attributeMap.put(vitalityModifier, 0);
        }
    }

    private int modifierCalculation(int mainStatValue) {
        int minusTen = mainStatValue - 10;
        float divideInHalf = (float) (minusTen / 2);
        return (int) Math.floor(divideInHalf);
    }

    public int getAttribute(CharacterAttribute specifier) {
        return attributeMap.get(specifier);
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        racialUniqueAttribute="";
        if (this.race == race) return;
        if (this.race != null) {
            implementAbilityScoreAffector(this.race, -1);
        }
        this.race = race;
        implementAbilityScoreAffector(race, 1);
    }

    private void implementAbilityScoreAffector(AbilityScoreChanger modifier, int additionOrSubtraction) {
        zeroOutStatsIfEmpty();
        for (Map.Entry<AbilityScore, Integer> entry : modifier.getAbilityScoreChanges().entrySet()) {
            AbilityScore specifier = entry.getKey();
            int valueToBeAddedOrSubtracted = additionOrSubtraction * entry.getValue();
            attributeMap.put(specifier, attributeMap.get(specifier) + valueToBeAddedOrSubtracted);
            setAbilityScoreModifier(specifier, attributeMap.get(specifier) + valueToBeAddedOrSubtracted);
        }
    }

    public void setRacialAttribute(RacialAttribute attribute) {
        if (this.racialAttribute == attribute) return;
        if (this.racialAttribute != null) {
            implementAbilityScoreAffector(this.racialAttribute, -1);
        }
        this.racialAttribute = attribute;
        implementAbilityScoreAffector(attribute, 1);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getStyleDescription(String style) {
        String styleDescription;
        switch (style) {
            case "Archery":
                styleDescription = "+2 bonus to attack rolls made with ranged weapons";
                break;
            case "Defense":
                styleDescription = "+1 bonus to AC while wearing armor";
                break;
            case "Dueling":
                styleDescription = "+2 bonus to damage rolls when holding a single weapon";
                break;
            case "Great Weapon Fighting":
                styleDescription = "You may re-roll the damage die while holding a two-handed weapon if you rolled a 1 or a 2";
                break;
            case "Protection":
                styleDescription = "When wielding a shield, you may impose a disadvantage on an enemy\n creature's attack roll when it attacks\na target" +
                        " other than you that is both within 5 feet of you and in your sight";
                break;
            case "Two-Weapon Fighting":
                styleDescription = "When you engage in two weapon fighting,\n you can add you ability modifier to the damage of the second attack";
                break;
            default:
                styleDescription = "No style found";
                break;
        }
        return styleDescription;
    }

    public VBox subRaceVbox = new VBox();

    public void racialSceneOptionSetter() {
        subRaceVbox.getChildren().clear();
        subRaceVbox.setAlignment(Pos.CENTER);
        subRaceVbox.setBackground(Background.EMPTY);
        subRaceVbox.setSpacing(10);
        switch (race) {
            case DWARF:
                ComboBox<String> dwarfSubRace = new ComboBox<>();
                dwarfSubRace.getItems().addAll("Hill Dwarf: +1 WIS", "Mountain Dwarf: +2 STR");
                subRaceVbox.getChildren().addAll(dwarfSubRace);
                dwarfSubRace.setOnAction(actionEvent -> setRacialAttribute(stringToRacialAttribute(dwarfSubRace.getValue())));
                break;
            case GNOME:
                ComboBox<String> gnomeSubRace = new ComboBox<>();
                gnomeSubRace.getItems().addAll("Forest Gnome: +1 DEX", "Rock Gnome: +1 CON");
                subRaceVbox.getChildren().addAll(gnomeSubRace);
                gnomeSubRace.setOnAction(actionEvent -> setRacialAttribute(stringToRacialAttribute(gnomeSubRace.getValue())));
                break;
            case HALFELF:
                //Below for iteration 3.  Not ready yet.
                    /*String[] statsForBoxes = new String[]{"STR", "DEX", "CON", "INT", "WIS", "CHA"};
                    HBox halfElfHbox = new HBox();
                    halfElfHbox.setAlignment(Pos.CENTER);
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
                    for (int i = 0; i < statsForBoxes.length; i++) {
                        CheckBox selBox = new CheckBox(statsForBoxes[i]);
                        selBox.selectedProperty().addListener(listener);
                        halfElfHbox.getChildren().add(selBox);
                        halfElfCheckboxes[i] = selBox;
                    }
                    subRaceVbox.getChildren().addAll(halfElfHbox);*/
                break;
            case HALFORC:
            case HUMAN:
            case TIEFLING:
            case ZEROMAN:
                break;
            case DRAGONBORN:
                ComboBox<String> breathWeaponSelection = new ComboBox<>();
                breathWeaponSelection.getItems().addAll("Black Dragon: Acid", "Blue Dragon: Lightning", "Brass Dragon: Fire", "Bronze Dragon: Lightning", "Copper Dragon: Acid",
                        "Gold Dragon: Fire", "Green Dragon: Poison", "Red Dragon: Fire", "Silver Dragon: Cold", "White Dragon: Cold");
                subRaceVbox.getChildren().addAll(breathWeaponSelection);
                breathWeaponSelection.setOnAction(actionEvent -> {
                            setRacialAttribute(stringToRacialAttribute(breathWeaponSelection.getValue()));
                            racialUniqueAttribute = breathWeaponSelection.getValue();
                        }
                );
                break;
            case ELF:
                ComboBox<String> elfSubRace = new ComboBox<>();
                elfSubRace.getItems().addAll("High Elf: +1 INT", "Wood Elf: +1 WIS", "Drow: +1 CHA");
                subRaceVbox.getChildren().addAll(elfSubRace);
                elfSubRace.setOnAction(actionEvent -> setRacialAttribute(stringToRacialAttribute(elfSubRace.getValue())));
                break;
            case HALFLING:
                ComboBox<String> halflingSubRace = new ComboBox<>();
                halflingSubRace.getItems().addAll("Lightfoot: +1 CHA", "Stout: +1 CON");
                subRaceVbox.getChildren().addAll(halflingSubRace);
                halflingSubRace.setOnAction(actionEvent -> setRacialAttribute(stringToRacialAttribute(halflingSubRace.getValue())));
                break;
        }
    }


    public void selectFighterProficiency(VBox innerProficiencyVbox, CharacterBackground background) {
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
            if ((skill.isFighterOption) && (!skill.equals(background.proficiency1) && (!skill.equals(background.proficiency2)))) {
                innerProficiencyVbox.getChildren().add(selbox);
                selbox.setOnAction(actionEvent -> {
                    if (selbox.isSelected()) {
                        checkBoxFlipper(skill);
                    }
                    setProficiencySkillsMap();
                });

            }
            fighterBoxes[counter] = selbox;
            counter++;
        }
    }

    public static RacialAttribute stringToRacialAttribute(String racialAttribute) {
        for (RacialAttribute attribute : RacialAttribute.values()) {
            if (attribute.attributeName.equals(racialAttribute)) {
                return attribute;
            }
        }
        return null;
    }
}