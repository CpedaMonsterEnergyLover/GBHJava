package ru.vovac.game.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import static ru.vovac.game.utils.ContentLocalizer.initLocalDirs;

public class JSONContentLoader <T> {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    private Path path;

    public JSONContentLoader(String path, Class<T> type) {
        this.path = Path.of(path);
    }

    public HashMap<Integer, T> loadContent() {
        long ms = System.currentTimeMillis();
        Gson gson = new Gson();
        String json;
        try {
            json = new String(Files.readAllBytes(Paths.get("json/", path.toString())));
            Type typeOfResourceHandler = new TypeToken<HashMap<Integer, T>>() { }.getType();
            HashMap<Integer, T> loaded = gson.fromJson(json, typeOfResourceHandler);
            // Вот здесь loaded должен передаваться в функцию, котора смотрит на все его элементы и создает
            // Нужные файлы в папке lang/NN/objects
            if (loaded != null) printLoadingDoneIn(path.toString(), ms, loaded.size());
            else loaded = new HashMap<>();
            if (loaded.size() > 0) initLocalDirs(path.toString());
            return loaded;
        } catch (IOException e) {
            printLoadingException(path.toString(), e);
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