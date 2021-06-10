package ru.vovac.game.objects;

import ru.vovac.game.objects.classes.LocalizableObject;
import ru.vovac.game.utils.Utils;

import java.util.List;

public class Enfilade extends LocalizableObject {
    private int enfiladeID;
    private String stringID;
    private List<Integer> floors;

    //Deserialization constructor
    public Enfilade(String json) {
        this.enfiladeID = Integer.parseInt(Utils.getSubstringValue(json, "enfiladeID"));
        this.stringID = Utils.getSubstringValue(json, "stringID");
        String floorsListString = Utils.getSubstringValue(json, "floors");
        this.floors = Utils.integerListFromString(floorsListString);
        this.addLocalizableField("title");
        this.addLocalizableField("description");
    }


    public int getEnfiladeID() {
        return enfiladeID;
    }

    public String getStringID() {
        return stringID;
    }

    public List<Integer> getFloors() {
        return floors;
    }

    @Override
    public String toString() {
        return "Enfilade{" +
                "enfiladeID=" + enfiladeID +
                ", stringID='" + stringID + '\'' +
                ", title=" + getLocalizableField("title") +
                ", description=" + getLocalizableField("description") +
                ", floors=" + floors +
                '}';
    }
}
