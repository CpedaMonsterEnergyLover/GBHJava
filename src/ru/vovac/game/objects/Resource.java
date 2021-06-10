package ru.vovac.game.objects;

import ru.vovac.game.objects.classes.LocalizableObject;
import ru.vovac.game.utils.Utils;

public class Resource extends LocalizableObject {
    private int resourceID;
    private String stringID;
    private int rarity;

    //Deserialization constructor
    public Resource(String json) {
        this.resourceID = Integer.parseInt(Utils.getSubstringValue(json, "resourceID"));
        this.stringID = Utils.getSubstringValue(json, "stringID");
        this.rarity = Integer.parseInt(Utils.getSubstringValue(json, "rarity"));
        this.addLocalizableField("title");
        this.addLocalizableField("description");
    }

    public int getResourceID() {
        return resourceID;
    }

    public String getStringID() {
        return stringID;
    }

    public int getDropRate() {
        return rarity;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "resourceID=" + resourceID +
                ", stringID='" + stringID + '\'' +
                ", dropRate=" + rarity +
                ", title=" + getLocalizableField("title") +
                ", description=" + getLocalizableField("description") +
                '}';
    }
}
