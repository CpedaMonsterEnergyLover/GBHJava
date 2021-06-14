package ru.vovac.game.objects;

import ru.vovac.game.classes.LocalizableObject;
import ru.vovac.game.utils.Utils;

import java.util.HashMap;

public class Equipment extends LocalizableObject {
    private int equipmentID;
    private String stringID;
    private int level;
    private int defense;
    private int mana;
    private int energy;
    private int variablesAmount;
    HashMap<String, Integer> variables;

    //Deserialization constructor
    public Equipment(String json) {
        this.equipmentID = Integer.parseInt(Utils.getSubstringValue(json, "equipmentID"));
        this.stringID = Utils.getSubstringValue(json, "stringID");
        this.level = Integer.parseInt(Utils.getSubstringValue(json, "level"));
        this.defense = Integer.parseInt(Utils.getSubstringValue(json, "defense"));
        this.mana = Integer.parseInt(Utils.getSubstringValue(json, "mana"));
        this.energy = Integer.parseInt(Utils.getSubstringValue(json, "energy"));
        this.variablesAmount = Integer.parseInt(Utils.getSubstringValue(json, "variablesAmount"));
        String mapOfVariables = Utils.getSubstringValue(json, "variables");
        this.variables = Utils.MapStringIntegerFromString(mapOfVariables);
        this.addLocalizableField("title");
    }
}
