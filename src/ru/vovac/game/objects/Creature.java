package ru.vovac.game.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.vovac.game.classes.EntityObject;
import ru.vovac.game.utils.Utils;

public class Creature extends EntityObject {
    private int creatureID;
    private String stringID;
    private int attack;

    @JsonIgnore
    private boolean aggressive;
    /* From super:
    private int maxHealth;
    private int defense;
    private int level;
    @JSONIgnore
    private int currentHealth
    */

    //Deserialization constructor
    public Creature(String json) {
        this.creatureID = Integer.parseInt(Utils.getSubstringValue(json, "creatureID"));
        this.stringID = Utils.getSubstringValue(json, "stringID");
        this.attack = Integer.parseInt(Utils.getSubstringValue(json, "attack"));
        this.defense = Integer.parseInt(Utils.getSubstringValue(json, "defense"));
        this.level = Integer.parseInt(Utils.getSubstringValue(json, "level"));
        this.maxHealth = Integer.parseInt(Utils.getSubstringValue(json, "maxHealth"));
        this.addLocalizableField("title");
    }

}
