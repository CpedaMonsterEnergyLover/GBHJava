package ru.vovac.game.objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Resource {
    private int resourceID;
    private int stringID;
    private String title;
    private String description;
    //private List<String> localizedFields;

    public Resource(int resourceID) {
        this.resourceID = resourceID;
    }
}
