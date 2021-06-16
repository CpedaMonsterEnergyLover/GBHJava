package ru.vovac.game.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jme3.export.*;
import ru.vovac.game.classes.EntityObject;
import ru.vovac.game.utils.Utils;
import ru.vovac.wrappers.IntegerArrayWrapper;
import ru.vovac.wrappers.IntegerIntegerHashMapWrapper;

import java.io.IOException;

public class Creature extends EntityObject implements Savable {
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

    @Override
    public void write(JmeExporter ex) throws IOException {
        OutputCapsule capsule = ex.getCapsule(this);
        capsule.write(creatureID,"creatureID", 1);
        capsule.write(stringID,"stringID", "");
        capsule.write(attack,"attack", 1);
        capsule.write(aggressive,"aggressive", Boolean.TRUE);
        capsule.write(maxHealth,"maxHealth", 1);
        capsule.write(defense,"defense", 1);
        capsule.write(level,"level", 1);
        capsule.write(currentHealth,"currentHealth", 1);
    }

    @Override
    public void read(JmeImporter im) throws IOException {
        InputCapsule capsule = im.getCapsule(this);
        creatureID = capsule.readInt("creatureID", 1);
        stringID = capsule.readString("stringID", "");
        attack = capsule.readInt("attack", 1);
        aggressive = capsule.readBoolean("aggressive", Boolean.TRUE);
        maxHealth = capsule.readInt("maxHealth", 1);
        defense = capsule.readInt("defense", 1);
        level = capsule.readInt("level", 1);
        currentHealth = capsule.readInt("currentHealth", 1);
    }
}
