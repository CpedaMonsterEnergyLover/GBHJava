package ru.vovac.wrappers;

import com.jme3.export.*;
import org.apache.commons.lang3.ArrayUtils;
import ru.vovac.game.objects.Hero;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HeroArrayWrapper implements ArrayWrapper<Hero>, Savable {
    private Hero[] content;

    public HeroArrayWrapper(Hero[] content) {
        this.content = content;
    }

    public HeroArrayWrapper(List<Hero> list){
        this.content = list.toArray(new Hero[1]);
    }

    public HeroArrayWrapper(){};

    @Override
    public Hero get(int index) {
        return ArrayUtils.get(content, index);
    }

    @Override
    public boolean isEmpty() {
        return ArrayUtils.isEmpty(content);
    }

    @Override
    public Hero getRandom() {
        return ArrayUtils.get(content, new Random().nextInt(content.length));
    }

    @Override
    public boolean contains(Hero s) {
        return ArrayUtils.contains(content, s);
    }

    @Override
    public List<Hero> unwrap() {
        return Arrays.asList(content);
    }

    @Override
    public void write(JmeExporter ex) throws IOException {
        OutputCapsule capsule = ex.getCapsule(this);
        capsule.write(content,"content", new Hero[1]);
    }

    @Override
    public void read(JmeImporter im) throws IOException {
        InputCapsule capsule = im.getCapsule(this);
        content = (Hero[]) capsule.readSavableArray("content",  new Hero[1]);
    }

    @Override
    public String toString() {
        return "HeroArrayWrapper{" +
                "content=" + Arrays.toString(content) +
                '}';
    }
}
