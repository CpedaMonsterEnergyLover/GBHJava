package ru.vovac.wrappers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.jme3.export.*;
import org.apache.commons.lang3.ArrayUtils;

public class StringArrayWrapper implements ArrayWrapper <String>, Savable {
    private String[] content;

    public StringArrayWrapper(String[] content) {
        this.content = content;
    }

    public StringArrayWrapper(List<String> list){
        this.content = list.toArray(new String[0]);
    }

    public StringArrayWrapper(){};

    @Override
    public String get(int index) {
        return ArrayUtils.get(content, index);
    }

    @Override
    public boolean isEmpty() {
        return ArrayUtils.isEmpty(content);
    }

    @Override
    public String getRandom() {
        return ArrayUtils.get(content, new Random().nextInt(content.length));
    }

    @Override
    public boolean contains(String s) {
        return ArrayUtils.contains(content, s);
    }

    @Override
    public List<String> unwrap() {
        return Arrays.asList(content);
    }

    @Override
    public void write(JmeExporter ex) throws IOException {
        OutputCapsule capsule = ex.getCapsule(this);
        capsule.write(content,"content", new String[]{});
    }

    @Override
    public void read(JmeImporter im) throws IOException {
        InputCapsule capsule = im.getCapsule(this);
        content = capsule.readStringArray("content",  new String[]{});
    }
}
