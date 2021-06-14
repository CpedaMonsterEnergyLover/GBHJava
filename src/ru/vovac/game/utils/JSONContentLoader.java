package ru.vovac.game.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import ru.vovac.game.classes.LocalizableObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class JSONContentLoader <T extends LocalizableObject> {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    private Path path;
    private Class tClass;

    public JSONContentLoader(String path, Class<T> tclass) {
        this.path = Path.of(path);
        this.tClass = tclass;
    }

    public HashMap<Integer, T> loadContent() {
        long ms = System.currentTimeMillis();
        ObjectMapper mapper = new ObjectMapper();
        String json;
        try {
            json = new String(Files.readAllBytes(Paths.get("json/", path.toString())));
            // If no objects has been added yet
            if (json.length() == 0) {
                return new HashMap<>();
            }
            MapType mapType = mapper.getTypeFactory().constructMapType(Map.class, Integer.class, tClass);
            HashMap<Integer, T> loaded = mapper.readValue(json, mapType);
            if (loaded != null) printLoadingDoneIn(path.toString(), ms, loaded.size());
            else loaded = new HashMap<>();
            // LOCALIZATION
            ContentLocalizator<T> contentLocalizator = new ContentLocalizator<>(loaded, path);
            HashMap<Integer, T> updated = contentLocalizator.localizeCollection();
            return updated;
        } catch (IOException e) {
            printLoadingException(path.toString(), e);
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    private static void printLoadingException(String res, Exception e) {
        System.out.println(ANSI_RED +  "[COLLECTION LOADER] [" + res + "] Loading error:" + ANSI_RESET);
        e.printStackTrace();
    }

    private static void printLoadingDoneIn(String res, long start, int len) {
        if (len > 0 ) {
            long ms = System.currentTimeMillis();
            System.out.println(ANSI_GREEN + "[COLLECTION LOADER] [" + res + "] Loaded " + len + " object(s) in " + (ms - start) + " ms" + ANSI_RESET);
        }
    }
}