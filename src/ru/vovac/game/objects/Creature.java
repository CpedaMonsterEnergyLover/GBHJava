package ru.vovac.game.objects;

import ru.vovac.game.objects.classes.EntityObject;
import ru.vovac.game.utils.Utils;

public class Creature extends EntityObject {
    private int creatureID;
    private String stringID;
    private int attack;
    private int defense;
    private int level;
    private int dice;

    //Deserialization constructor
    public Creature(String json) {
        this.creatureID = Integer.parseInt(Utils.getSubstringValue(json, "creatureID"));
        this.stringID = Utils.getSubstringValue(json, "stringID");
        this.attack = Integer.parseInt(Utils.getSubstringValue(json, "attack"));
        this.defense = Integer.parseInt(Utils.getSubstringValue(json, "defense"));
        this.level = Integer.parseInt(Utils.getSubstringValue(json, "level"));
        this.dice = Integer.parseInt(Utils.getSubstringValue(json, "dice"));
        this.addLocalizableField("title");
    }

    //TODO getters
}
