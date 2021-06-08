package ru.vovac.game.objects;

import ru.vovac.game.objects.classes.GameObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Location extends GameObject {
    private int cardID;
    private String stringID;
    private String title;
    private int rarity;
    private String description;
    private Map<String, Double> resourceDrops = new HashMap<>();
    private List<Hero> heroes = new ArrayList<>();
    private List<Creature> creatures = new ArrayList<>();
    //private List<String> localizedFields;

    public Location(int cardID, String stringID, String title, int rarity, String description) {
        this.cardID = cardID;
        this.stringID = stringID;
        this.title = title;
        this.rarity = rarity;
        this.description = description;
    }

    public Location newGameObject() {
        return new Location(this.cardID, this.stringID, this.title, this.rarity, this.description);
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public String getStringID() {
        return stringID;
    }

    public void setStringID(String stringID) {
        this.stringID = stringID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    public void setCreatures(List<Creature> creatures) {
        this.creatures = creatures;
    }

    public Map<String, Double> getResourceDrops() {
        return resourceDrops;
    }

    public void setResourceDrops(Map<String, Double> resourceDrops) {
        this.resourceDrops = resourceDrops;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardID=" + cardID +
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
