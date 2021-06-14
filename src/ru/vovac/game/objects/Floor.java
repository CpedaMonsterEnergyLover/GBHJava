package ru.vovac.game.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.vovac.game.classes.LocalizableObject;
import ru.vovac.game.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static main.java.CollectionLoader.locationCollection;

public class Floor extends LocalizableObject {
    private int floorID;
    private String stringID;
    private List<Integer> allLocations;
    @JsonIgnore
    private List<Integer> commonLocations = new ArrayList<>();
    @JsonIgnore
    private List<Integer> rareLocations = new ArrayList<>();
    @JsonIgnore
    private List<Integer> epicLocations = new ArrayList<>();
    @JsonIgnore
    private List<Integer> storyLocations = new ArrayList<>();

    //Deserialization constructor
    public Floor(String json) {
        this.floorID = Integer.parseInt(Utils.getSubstringValue(json, "floorID"));
        this.stringID = Utils.getSubstringValue(json, "stringID");
        String allLocationsString = Utils.getSubstringValue(json, "allLocations");
        this.allLocations = Utils.integerListFromString(allLocationsString);
        this.addLocalizableField("title");
        this.addLocalizableField("description");
    }

    public void sortRarities() {
        if (allLocations == null) return;
        for (Integer i : allLocations) {
            Location location = locationCollection.get(i);
            if (location == null) break;
            int rarity = location.getRarity();
            if (rarity == 0)
                storyLocations.add(i);
            if (rarity == 3)
                epicLocations.add(i);
            if (rarity == 2)
                rareLocations.add(i);
            if (rarity == 1)
                commonLocations.add(i);
        }
    }


    public int getFloorID() {
        return floorID;
    }

    public String getStringID() {
        return stringID;
    }

    public List<Integer> getAllLocations() {
        return allLocations;
    }

    public List<Integer> getCommonLocations() {
        return commonLocations;
    }

    public List<Integer> getRareLocations() {
        return rareLocations;
    }

    public List<Integer> getEpicLocations() {
        return epicLocations;
    }

    public List<Integer> getStoryLocations() {
        return storyLocations;
    }

    @Override
    public String toString() {
        return "Floor{" +
                "floorID=" + floorID +
                ", stringID='" + stringID + '\'' +
                ", title=" + getLocalizableField("title") +
                ", description=" + getLocalizableField("description") +
                ", allLocations=" + allLocations +
                ", commonLocations=" + commonLocations +
                ", rareLocations=" + rareLocations +
                ", epicLocations=" + epicLocations +
                ", storyLocations=" + storyLocations +
                '}';
    }
}
