package ru.vovac.game.objects;

import ru.vovac.game.classes.LocalizableObject;
import ru.vovac.game.utils.Utils;

import java.util.HashMap;

public class Ability extends LocalizableObject {
    private int abilityID;
    private String stringID;
    private int variablesAmount;
    private int maxLevel;
    private boolean removeable;
    HashMap<String, Integer> levelBind;
    HashMap<String, Integer> variables;

    // Deserialization constructor
    public Ability(String json) {
        this.abilityID = Integer.parseInt(Utils.getSubstringValue(json, "abilityID"));
        this.stringID = Utils.getSubstringValue(json, "stringID");
        this.variablesAmount = Integer.parseInt(Utils.getSubstringValue(json, "variablesAmount"));
        this.maxLevel = Integer.parseInt(Utils.getSubstringValue(json, "maxLevel"));
        this.removeable = Boolean.parseBoolean(Utils.getSubstringValue(json, "removeable"));
        String mapOfLevelBind = Utils.getSubstringValue(json, "levelBind");
        this.levelBind = Utils.MapStringIntegerFromString(mapOfLevelBind);
        String mapOfVariables = Utils.getSubstringValue(json, "variables");
        this.variables =  Utils.MapStringIntegerFromString(mapOfVariables);
        this.addLocalizableField("title");
        this.addLocalizableField("description");
    }

    @Override
    public String toString() {
        return "Ability{" +
                "abilityID=" + abilityID +
                ", stringID='" + stringID + '\'' +
                ", variablesAmount=" + variablesAmount +
                ", maxLevel=" + maxLevel +
                ", removeable=" + removeable +
                ", levelBind=" + levelBind +
                ", variables=" + variables +
                '}';
    }
}
