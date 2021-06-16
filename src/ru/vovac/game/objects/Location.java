package ru.vovac.game.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jme3.export.*;
import ru.vovac.game.classes.GameObject;
import ru.vovac.game.utils.Utils;
import ru.vovac.wrappers.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Location extends GameObject implements Savable{
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

    // Deserialization constructor
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

    // Cloning constructor
    public Location(int locationID, String stringID, int rarity, List<String> resourceDrops, int containsEvent, List<Integer> monsters, List<Hero> heroes, List<Creature> creatures) {
        this.locationID = locationID;
        this.stringID = stringID;
        this.rarity = rarity;
        this.resourceDrops = resourceDrops;
        this.containsEvent = containsEvent;
        this.monsters = monsters;
        this.heroes = heroes;
        this.creatures = creatures;
    }

    // Empty constructor for the Savable implementation
    public Location(){}

    public Location newGameObject() {
        return new Location(this.locationID, this.stringID, this.rarity, this.resourceDrops, this.containsEvent, this.monsters, this.heroes, this.creatures);
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

    @Override
    public void write(JmeExporter ex) throws IOException {
        OutputCapsule capsule = ex.getCapsule(this);
        capsule.write(locationID,   "locationID",   1);
        capsule.write(stringID,   "stringID", "");
        capsule.write(rarity,   "rarity",   1);
        capsule.write(containsEvent,   "containsEvent",   1);
        capsule.write(new StringArrayWrapper(resourceDrops),   "resourceDrops", new StringArrayWrapper());
        capsule.write(new IntegerArrayWrapper(monsters),   "monsters",   new IntegerArrayWrapper());
        capsule.write(new HeroArrayWrapper(heroes),   "heroes",   new HeroArrayWrapper());
        capsule.write(new CreatureArrayWrapper(creatures),   "creatures",   new HeroArrayWrapper());
    }

    @Override
    public void read(JmeImporter im) throws IOException {
        InputCapsule capsule = im.getCapsule(this);
        locationID = capsule.readInt("locationID", 1);
        stringID = capsule.readString("stringID", "");
        rarity = capsule.readInt("rarity", 1);
        StringArrayWrapper resourceDropsUnwrapped = (StringArrayWrapper) capsule.readSavable
                ("resourceDrops", new StringArrayWrapper());
        resourceDrops = resourceDropsUnwrapped.unwrap();
        IntegerArrayWrapper monstersUnwrapped = (IntegerArrayWrapper) capsule.readSavable
                ("monsters", new IntegerArrayWrapper());
        monsters = monstersUnwrapped.unwrap();
        HeroArrayWrapper heroesUnwrapped = (HeroArrayWrapper) capsule.readSavable
                ("heroes", new HeroArrayWrapper());
        heroes = heroesUnwrapped.unwrap();
        CreatureArrayWrapper creaturesUnwrapped = (CreatureArrayWrapper) capsule.readSavable
                ("creatures", new CreatureArrayWrapper());
        creatures = creaturesUnwrapped.unwrap();
    }
}
