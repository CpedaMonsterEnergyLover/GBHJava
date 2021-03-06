package ru.vovac.game.utils;

import com.jme3.math.Vector3f;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {

    public static List<Integer> integerListFromString(String s) {
        return Stream.of(s.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static List<String> stringListFromString(String s) {
        return Stream.of(s.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    // Parses a string with format "a=1,2,3. b=1,2,3. c=1,2,3" into HashMap<String, List<Integer>>
    public static HashMap<String, List<Integer>> MapStringListIntegerFromString(String s) {
        if (s == null) return null;
        HashMap<String, List<Integer>> result = new HashMap<>();
        String m = s.replaceAll("\\s", "");
        String[] lines = m.split("\\.");
        for (String v : lines) {
            String[] values = v.split("=");
            result.put(values[0], integerListFromString(values[1]));
        }

        return result;
    }

    public static HashMap<String, Integer> MapStringIntegerFromString(String s) {
        if (s == null) return null;
        HashMap<String, Integer> result = new HashMap<>();
        String m = s.replaceAll("\\s", "");
        String[] lines = m.split("\\.");
        for (String v : lines) {
            String[] values = v.split("=");
            result.put(values[0], Integer.parseInt(values[1]));
        }
        return result;
    }

    public static String getSubstringValue(String m, String s) {
        m = m.replaceAll("\\s", "");
        String[] values = m.split(";");
        String result = null;
        for (int i = 0; i < values.length; i++) {
            String[] pair = values[i].split(":");
            if (pair[0].equals(s)) {
                if (pair.length > 1) result = pair[1];
            }
        }
        return result;
    }

    public static float ensureRange(float value, float min, float max) {
        return Math.min(Math.max(value, min), max);
    }

    public static int randomDir() {
        int dir = 0;
        while (dir == 0) dir = new Random().nextInt(3) - 1;
        return dir;
    }
}
