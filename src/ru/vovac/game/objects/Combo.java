package ru.vovac.game.objects;

import ru.vovac.game.classes.LocalizableObject;
import ru.vovac.game.utils.Utils;

import java.util.HashMap;
import java.util.List;

public class Combo extends LocalizableObject {
    private int comboID;
    private String stringID;
    private int variablesAmount;
    private HashMap<String, List<Integer>> variables;

    //Deserialization constructor
    public Combo(String json) {
        this.comboID = Integer.parseInt(Utils.getSubstringValue(json, "comboID"));
        this.stringID = Utils.getSubstringValue(json, "stringID");
        String variablesString = Utils.getSubstringValue(json,"variables");
        this.variables = Utils.MapStringListIntegerFromString(variablesString);
        this.variablesAmount = Integer.parseInt(Utils.getSubstringValue(json, "variablesAmount"));

        this.addLocalizableField("title");
        this.addLocalizableField("description");
    }


    @Override
    public String toString() {
        return "Combo{" +
                "comboID=" + comboID +
                ", stringID='" + stringID + '\'' +
                ", variablesAmount=" + variablesAmount +
                ", variables=" + variables +
                '}';
    }
}
