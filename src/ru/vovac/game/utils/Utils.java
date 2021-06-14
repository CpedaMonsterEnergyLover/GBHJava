package ru.vovac.game.utils;

import java.util.HashMap;
import java.util.List;
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
        HashMap<String, List<Integer>> result = new HashMap<>();
        String m = s.replaceAll("\\s", "");
        String[] lines = m.split("\\.");
        for (String v : lines) {
            String[] values = v.split("=");
            result.put(values[0], integerListFromString(values[1]));
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
                result = pair[1];
            }
        }
        return result;
    }
}
