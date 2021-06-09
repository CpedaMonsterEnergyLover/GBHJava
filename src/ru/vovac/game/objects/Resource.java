package ru.vovac.game.objects;

import ru.vovac.game.objects.classes.LocalizableObject;
import ru.vovac.game.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Resource extends LocalizableObject {
    private int resourceID;
    private String stringID;
    private double dropRate;
    private String title;
    private String description;
    //private List<String> localizedFields;

    //Deserialization constructor
    public Resource(String json) {
        this.resourceID = Integer.parseInt(Utils.getSubstringValue(json, "resourceID"));
        this.stringID = Utils.getSubstringValue(json, "stringID");
        this.dropRate = Integer.parseInt(Utils.getSubstringValue(json, "dropRate"));
        this.addLocalizableField("title");
        this.addLocalizableField("description");
    }

    public Resource(int resourceID) {
        this.resourceID = resourceID;
    }
}
