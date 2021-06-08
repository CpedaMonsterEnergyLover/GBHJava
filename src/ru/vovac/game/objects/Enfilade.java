package ru.vovac.game.objects;

import java.util.List;

public class Enfilade {
    private int enfiladeID;
    private String stringID;
    private String title;
    private String description;
    private List<Integer> floors;
    //private List<String> localizedFields;

    @Override
    public String toString() {
        return "Enfilade{" +
                "enfiladeID=" + enfiladeID +
                ", stringID='" + stringID + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", floors=" + floors +
                '}';
    }
}
