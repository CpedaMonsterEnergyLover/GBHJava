package ru.vovac.game.objects;

import ru.vovac.game.objects.classes.GameObject;

import java.util.List;

public class Event extends GameObject {
    private int cardID;
    private String stringID;
    private String title;
    private int rarity;
    private String description;
    //private List<String> localizedFields;

    public Event(int cardID, String stringID, String title, int rarity, String description) {
        this.cardID = cardID;
        this.stringID = stringID;
        this.title = title;
        this.rarity = rarity;
        this.description = description;
    }

    public Event newGameObject() {
        return new Event(this.cardID, this.stringID, this.title, this.rarity, this.description);
    }

    @Override
    public String toString() {
        return "Event{" +
                "cardID=" + cardID +
                ", stringID='" + stringID + '\'' +
                ", title='" + title + '\'' +
                ", rarity=" + rarity +
                ", description='" + description + '\'' +
                '}';
    }
}
