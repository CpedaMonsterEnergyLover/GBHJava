package ru.vovac.game.objects.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;

public class EntityObject extends LocalizableObject{
    @JsonIgnore
    private int idCounter = 0;
    @JsonIgnore
    private final int objectID = ++idCounter;
    @JsonIgnore
    private int currentHealth;
    @JsonIgnore
    private HashMap<Integer, Integer> buffs;

    private int health;


}
