package ru.vovac.game.objects;

import ru.vovac.game.objects.classes.LocalizableObject;
import ru.vovac.game.utils.Utils;

import java.util.HashMap;
import java.util.List;

public class Dice extends LocalizableObject {
    private int diceID;
    private String stringID;
    private int level;
    private HashMap<String, List<Integer>> energyMatrix;
    private int comboID;
    private int procID;

    //Deserialization constructor
    public Dice(String json) {
        this.diceID = Integer.parseInt(Utils.getSubstringValue(json, "diceID"));
        this.stringID = Utils.getSubstringValue(json, "stringID");
        String energyMatrixString = Utils.getSubstringValue(json,"energyMatrix");
        this.energyMatrix = Utils.MapStringListIntegerFromString(energyMatrixString);
        this.level = Integer.parseInt(Utils.getSubstringValue(json, "level"));
        this.comboID = Integer.parseInt(Utils.getSubstringValue(json, "comboID"));
        this.procID = Integer.parseInt(Utils.getSubstringValue(json, "procID"));
        this.addLocalizableField("title");
        this.addLocalizableField("description");
    }

    @Override
    public String toString() {
        return "Dice{" +
                "diceID=" + diceID +
                ", stringID='" + stringID + '\'' +
                ", level=" + level +
                ", energyMatrix=" + energyMatrix +
                ", comboID=" + comboID +
                ", procID=" + procID +
                '}';
    }

    //TODO getters;
}
