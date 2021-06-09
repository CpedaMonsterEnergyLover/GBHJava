package ru.vovac.game.objects;

import ru.vovac.game.objects.classes.GameObject;
import ru.vovac.game.utils.Utils;

public class Event extends GameObject {
    private int eventID;
    private String stringID;
    private int rarity;

    public Event(int cardID, String stringID, String title, int rarity, String description) {
        this.stringID = stringID;
        this.rarity = rarity;
    }

    // Deserialization constructor
    public Event(String json) {
        this.eventID = Integer.parseInt(Utils.getSubstringValue(json, "eventID"));
        this.stringID = Utils.getSubstringValue(json, "stringID");
        this.rarity = Integer.parseInt(Utils.getSubstringValue(json, "rarity"));
        this.addLocalizableField("title");
        this.addLocalizableField("description");
    }

    public String getStringID() {
        return stringID;
    }

    public int getEventID() {
        return eventID;
    }

    public int getRarity() {
        return rarity;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventID=" + eventID +
                ", stringID='" + stringID + '\'' +
                ", rarity=" + rarity +
                ", title=" + getLocalizableField("title") +
                ", description=" + getLocalizableField("description") +
                '}';
    }
}
