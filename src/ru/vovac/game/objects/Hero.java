package ru.vovac.game.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jme3.export.*;
import ru.vovac.game.classes.EntityObject;
import ru.vovac.game.utils.Utils;
import ru.vovac.wrappers.IntegerArrayWrapper;
import ru.vovac.wrappers.IntegerIntegerHashMapWrapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Hero extends EntityObject implements Savable {
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

    @Override
    public void write(JmeExporter ex) throws IOException {
        OutputCapsule capsule = ex.getCapsule(this);
        capsule.write(heroID,"heroID", 1);
        capsule.write(stringID,"stringID", "");
        capsule.write(heroType,"heroType", "");
        capsule.write(armorType,"armorType", "");
        capsule.write(new IntegerArrayWrapper(availableAbilities) ,"availableAbilities", new IntegerArrayWrapper());
        capsule.write(new IntegerIntegerHashMapWrapper(equipment),"equipment", new IntegerIntegerHashMapWrapper());
        capsule.write(new IntegerArrayWrapper(pickedAbilities) ,"pickedAbilities", new IntegerArrayWrapper());
        capsule.write(maxHealth,"maxHealth", 1);
        capsule.write(defense,"defense", 1);
        capsule.write(level,"level", 1);
        capsule.write(currentHealth,"currentHealth", 1);
    }

    @Override
    public void read(JmeImporter im) throws IOException {
        InputCapsule capsule = im.getCapsule(this);
        heroID = capsule.readInt("heroID", 1);
        stringID = capsule.readString("stringID", "");
        heroType = capsule.readString("heroType", "");
        armorType = capsule.readString("armorType", "");
        IntegerArrayWrapper availableAbilitiesUnwrapped = (IntegerArrayWrapper) capsule.readSavable
                ("availableAbilities", new IntegerArrayWrapper());
        availableAbilities = availableAbilitiesUnwrapped.unwrap();
        IntegerIntegerHashMapWrapper equipmentUnwrapped = (IntegerIntegerHashMapWrapper) capsule.readSavable
                ("equipment", new IntegerIntegerHashMapWrapper());
        equipment = equipmentUnwrapped.unwrap();
        IntegerArrayWrapper pickedAbilitiesUnwrapped = (IntegerArrayWrapper) capsule.readSavable
                ("availableAbilities", new IntegerArrayWrapper());
        pickedAbilities = pickedAbilitiesUnwrapped.unwrap();
        maxHealth = capsule.readInt("maxHealth", 1);
        defense = capsule.readInt("defense", 1);
        level = capsule.readInt("level", 1);
        currentHealth = capsule.readInt("keys", 1);

    }

}
