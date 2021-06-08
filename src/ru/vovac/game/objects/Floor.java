package ru.vovac.game.objects;

import java.util.ArrayList;
import java.util.List;

import static main.java.CollectionLoader.locationCollection;

public class Floor {
    private int floorID;
    private String stringID;
    private String title;
    private String description;
    private List<Integer> allLocations;
    private List<Integer> commonLocations = new ArrayList<>();
    private List<Integer> rareLocations = new ArrayList<>();
    private List<Integer> epicLocations = new ArrayList<>();
    private List<Integer> storyLocations = new ArrayList<>();
    //private List<String> localizedFields;

    public Floor() { }

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

    @Override
    public String toString() {
        return "Floor{" +
                "floorID=" + floorID +
                ", stringID='" + stringID + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", allLocations=" + allLocations +
                ", commonLocations=" + commonLocations +
                ", rareLocations=" + rareLocations +
                ", epicLocations=" + epicLocations +
                ", storyLocations=" + storyLocations +
                '}';
    }
}
