package ru.vovac.game.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.vovac.game.objects.classes.GameObject;
import ru.vovac.game.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Location extends GameObject {
    private int locationID;
    private String stringID;
    private int rarity;
    private List<String> resourceDrops;
    private int containsEvent;
    private List<Integer> monsters;
    @JsonIgnore
    private List<Hero> heroes = new ArrayList<>();
    @JsonIgnore
    private List<Creature> creatures = new ArrayList<>();

    //Deserialization constructor
    public Location(String json) {
        this.locationID = Integer.parseInt(Utils.getSubstringValue(json, "locationID"));
        this.stringID = Utils.getSubstringValue(json, "stringID");
        this.rarity = Integer.parseInt(Utils.getSubstringValue(json, "rarity"));
        String resourceDropsString = Utils.getSubstringValue(json, "resourceDrops");
        this.resourceDrops = Utils.stringListFromString(resourceDropsString);
        this.containsEvent = Integer.parseInt(Utils.getSubstringValue(json, "containsEvent"));
        String monstersString = Utils.getSubstringValue(json, "monsters");
        this.monsters = Utils.integerListFromString(monstersString);
        this.addLocalizableField("title");
        this.addLocalizableField("description");
    }

    public int getLocationID() {
        return locationID;
    }

    public String getStringID() {
        return stringID;
    }

    public int getRarity() {
        return rarity;
    }

    public List<String> getResourceDrops() {
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
                ", rarity=" + rarity +
                ", resourceDrops=" + resourceDrops +
                ", heroes=" + heroes +
                ", creatures=" + creatures +
                ", containsEvent=" + containsEvent +
                ", monsters=" + monsters +
                ", title=" + getLocalizableField("title") +
                ", description=" + getLocalizableField("description") +
                '}';
    }
}
