package ru.vovac.wrappers;

import com.jme3.export.*;
import org.apache.commons.lang3.ArrayUtils;
import ru.vovac.game.objects.Creature;
import ru.vovac.game.objects.Hero;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CreatureArrayWrapper implements ArrayWrapper<Creature>, Savable {
    private Creature[] content;

    public CreatureArrayWrapper(Creature[] content) {
        this.content = content;
    }

    public CreatureArrayWrapper(List<Creature> list){
        this.content = list.toArray(new Creature[1]);
    }

    public CreatureArrayWrapper(){};

    @Override
    public Creature get(int index) {
        return ArrayUtils.get(content, index);
    }

    @Override
    public boolean isEmpty() {
        return ArrayUtils.isEmpty(content);
    }

    @Override
    public Creature getRandom() {
        return ArrayUtils.get(content, new Random().nextInt(content.length));
    }

    @Override
    public boolean contains(Creature s) {
        return ArrayUtils.contains(content, s);
    }

    @Override
    public List<Creature> unwrap() {
        return Arrays.asList(content);
    }

    @Override
    public void write(JmeExporter ex) throws IOException {
        OutputCapsule capsule = ex.getCapsule(this);
        capsule.write(content,"content", new Creature[1]);
    }

    @Override
    public void read(JmeImporter im) throws IOException {
        InputCapsule capsule = im.getCapsule(this);
        content = (Creature[]) capsule.readSavableArray("content",  new Creature[1]);
    }

    @Override
    public String toString() {
        return "CreatureArrayWrapper{" +
                "content=" + Arrays.toString(content) +
                '}';
    }
}
