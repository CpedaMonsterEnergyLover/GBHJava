package ru.vovac.game.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.vovac.game.classes.EntityObject;
import ru.vovac.game.utils.Utils;

import java.util.HashMap;
import java.util.List;

public class Hero extends EntityObject {
    private int heroID;
    private String stringID;

    private String heroType; //“melee”, “magical”, “hybrid”
    private String armorType; //(“light”, “heavy”)

    List<Integer> availableAbilities; //Массив со способностями, которые ДОСТУПНЫ герою

    @JsonIgnore
    HashMap<Integer, Integer> equipment; //ключ — айди слота со снаряжением (1-оружие, 2-доспех, 3-бижутерия, 4-бижутерия) значение — айди предмета
    @JsonIgnore
    List<Integer> pickedAbilities;

    //Deserialization constructor
    public Hero(String json) {
        this.heroID = Integer.parseInt(Utils.getSubstringValue(json, "heroID"));
        this.stringID = Utils.getSubstringValue(json, "stringID");
        this.heroType = Utils.getSubstringValue(json, "heroType     ");
        this.armorType = Utils.getSubstringValue(json, "armorType");
        this.defense = Integer.parseInt(Utils.getSubstringValue(json, "defense"));
        this.level = Integer.parseInt(Utils.getSubstringValue(json, "level"));
        this.maxHealth = Integer.parseInt(Utils.getSubstringValue(json, "maxHealth"));
        String listOfAvailableAbilities = Utils.getSubstringValue(json, "availableAbilities");
        this.availableAbilities = Utils.integerListFromString(listOfAvailableAbilities);
        this.addLocalizableField("title");
        this.addLocalizableField("description");
    }

    /* From super:
    private int maxHealth;
    private int defense;
    private int level;
    @JSONIgnore
    private int currentHealth
    */


}
