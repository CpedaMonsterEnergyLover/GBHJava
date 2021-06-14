package ru.vovac.game.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;

public class EntityObject extends LocalizableObject{
    @JsonIgnore
    protected int idCounter = 0;
    @JsonIgnore
    protected final int objectID = ++idCounter;
    @JsonIgnore
    protected int currentHealth;
    @JsonIgnore
    protected HashMap<Integer, Integer> buffs;

    protected int maxHealth;
    protected int defense;
    protected int level;

}
