package ru.vovac.game.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.vovac.game.objects.classes.GameObject;
import ru.vovac.game.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Location extends GameObject {
    private int locationID;
    private String stringID;
    private String title;
    private int rarity;
    private String description;
    private List<Integer> resourceDrops = new ArrayList<>();
    @JsonIgnore
    private List<Hero> heroes = new ArrayList<>();
    @JsonIgnore
    private List<Creature> creatures = new ArrayList<>();
    //private List<String> localizedFields;

    public Location(int cardID, String stringID, String title, int rarity, String description) {
        this.locationID = cardID;
        this.stringID = stringID;
        this.title = title;
        this.rarity = rarity;
        this.description = description;
    }

    //Deserialization constructor
    public Location(String json) {
        this.locationID = Integer.parseInt(Utils.getSubstringValue(json, "floorID"));
        this.stringID = Utils.getSubstringValue(json, "stringID");
        this.rarity = Integer.parseInt(Utils.getSubstringValue(json, "rarity"));
        String resourceDropsString = Utils.getSubstringValue(json, "resourceDrops");
        this.resourceDrops = Utils.integerListFromString(resourceDropsString);
        this.addLocalizableField("title");
        this.addLocalizableField("description");
    }

    public int getLocationID() {
        return locationID;
    }

    public String getStringID() {
        return stringID;
    }

    public String getTitle() {
        return title;
    }

    public int getRarity() {
        return rarity;
    }

    public String getDescription() {
        return description;
    }

    public List<Integer> getResourceDrops() {
        return resourceDrops;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardID=" + locationID +
                ", stringID='" + stringID + '\'' +
                ", title='" + title + '\'' +
                ", rarity=" + rarity +
                ", description='" + description + '\'' +
                ", resourceDrops=" + resourceDrops +
                ", heroes=" + heroes +
                ", creatures=" + creatures +
                '}';
    }
}
