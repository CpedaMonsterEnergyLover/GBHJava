package ru.vovac.wrappers;

import com.jme3.export.*;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

public class IntegerIntegerHashMapWrapper implements HashMapWrapper<Integer, Integer>, Savable {
    private int[] keys;
    private int[] values;

    public IntegerIntegerHashMapWrapper(HashMap<Integer, Integer> map){
        Set<Integer> keySet = map.keySet();
        Collection<Integer> valueSet = map.values();
        this.keys = keySet.stream().mapToInt(i->i).toArray();
        this.values = valueSet.stream().mapToInt(i->i).toArray();
    }

    public IntegerIntegerHashMapWrapper(){};

    @Override
    public Integer get(Integer key) {
        Integer[] wrappedKeys = IntStream.of( keys ).boxed().toArray( Integer[]::new );
        Integer[] wrappedValues = IntStream.of( values ).boxed().toArray( Integer[]::new );
        int keyIndex = ArrayUtils.indexOf(wrappedKeys, key);
        return ArrayUtils.contains(wrappedKeys, key) ? ArrayUtils.get(wrappedValues, keyIndex) : null;
    }

    @Override
    public boolean isEmpty() {
        return ArrayUtils.isEmpty(keys);
    }

    @Override
    public boolean containsKey(Integer s) {
        return ArrayUtils.contains(keys, s);
    }

    @Override
    public boolean containsValue(Integer s) {
        return ArrayUtils.contains(values, s);
    }

    @Override
    public HashMap<Integer, Integer> unwrap() {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : keys) map.put(i, values[ArrayUtils.indexOf(values, i)]);
        return map;
    }

    @Override
    public void write(JmeExporter ex) throws IOException {
        OutputCapsule capsule = ex.getCapsule(this);
        capsule.write(keys,"keys", new int[1]);
        capsule.write(values,"content", new int[1]);
    }

    @Override
    public void read(JmeImporter im) throws IOException {
        InputCapsule capsule = im.getCapsule(this);
        keys = capsule.readIntArray("keys", new int[1]);
        values = capsule.readIntArray("values", new int[1]);
    }

    @Override
    public String toString() {
        return "IntegerIntegerHashMapWrapper{" +
                "keys=" + Arrays.toString(keys) +
                ", values=" + Arrays.toString(values) +
                '}';
    }
}
