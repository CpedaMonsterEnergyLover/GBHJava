package ru.vovac.wrappers;

import com.jme3.export.*;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class IntegerArrayWrapper implements ArrayWrapper<Integer>, Savable {
    private int[] content;

    public IntegerArrayWrapper(int[] content) {
        this.content = content;
    }

    public IntegerArrayWrapper(List<Integer> list){
        this.content = list.stream().mapToInt(Integer::intValue).toArray();
    }

    public IntegerArrayWrapper(){};

    @Override
    public Integer get(int index) {
        Integer[] wrappedContent = IntStream.of( content ).boxed().toArray( Integer[]::new );
        return ArrayUtils.get(wrappedContent, index);
    }

    @Override
    public boolean isEmpty() {
        return ArrayUtils.isEmpty(content);
    }

    @Override
    public Integer getRandom() {
        Integer[] wrappedContent = IntStream.of( content ).boxed().toArray( Integer[]::new );
        return ArrayUtils.get(wrappedContent, new Random().nextInt(content.length));
    }

    @Override
    public boolean contains(Integer s) {
        return ArrayUtils.contains(content, s);
    }

    @Override
    public List<Integer> unwrap() {
        return Arrays.asList(IntStream.of( content ).boxed().toArray( Integer[]::new));
    }

    @Override
    public void write(JmeExporter ex) throws IOException {
        OutputCapsule capsule = ex.getCapsule(this);
        capsule.write(content,"content", new int[1]);
    }

    @Override
    public void read(JmeImporter im) throws IOException {
        InputCapsule capsule = im.getCapsule(this);
        content = capsule.readIntArray("content", new int[1]);
    }
}
