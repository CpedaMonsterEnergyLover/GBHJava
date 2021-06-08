package ru.vovac.game.config;

import com.google.gson.Gson;
import ru.vovac.game.utils.LangsEnum;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.vovac.game.utils.JSONContentLoader.*;

public class Config {
    public static final Path CONFIG_PATH = Path.of("json/config/config.json"); // DO NOT CHANGE THIS!!!
    private static Config instance = new Config();

    private String CURRENT_LANG = null;
    private List<String> AVAILABLE_LANGUAGES = null;
    private String JSON_CREATURES = null;
    private String JSON_EVENTS = null;
    private String JSON_FLOORS = null;
    private String JSON_HEROES = null;
    private String JSON_LOCATIONS = null;
    private String JSON_RESOURCES = null;
    private String JSON_ENFILADES = null;

    public static void load(){
        Gson gson = new Gson();
        String json = null;
        try {
            long ms = System.currentTimeMillis();
            json = new String(Files.readAllBytes(Paths.get(CONFIG_PATH.toString())));
            Config.instance = gson.fromJson(json, Config.class);
            int len = instance.getColumnCount();
            List<String> missingColumns = instance.getMissingColumns();
            if (missingColumns == null) {
                printConfigLoaded(ms, len - 2);
            } else {
                printConfigNotValidated(missingColumns);
            }

        } catch (IOException e) {
            printConfigNotLoaded(e);
        }
    }


    public static String CURRENT_LANG() {
        return instance.CURRENT_LANG;
    }

    public static String JSON_CREATURES() {
        return instance.JSON_CREATURES;
    }

    public static String JSON_EVENTS() {
        return instance.JSON_EVENTS;
    }

    public static String JSON_FLOORS() {
        return instance.JSON_FLOORS;
    }

    public static String JSON_HEROES() {
        return instance.JSON_HEROES;
    }

    public static String JSON_LOCATIONS() {
        return instance.JSON_LOCATIONS;
    }

    public static String JSON_RESOURCES() {
        return instance.JSON_RESOURCES;
    }

    public static String JSON_ENFILADES() {
        return instance.JSON_ENFILADES;
    }

    public static List<String> AVAILABLE_LANGUAGES() {
        return instance.AVAILABLE_LANGUAGES;
    }

    public static String getString() {
        if (instance == null) return "";
        return "Config{" +
                "LANGUAGE='" + instance.CURRENT_LANG + '\'' +
                ",AVAILABLE_LANGUAGES='" + instance.AVAILABLE_LANGUAGES + '\'' +
                ", JSON_CREATURES='" + instance.JSON_CREATURES + '\'' +
                ", JSON_EVENTS='" + instance.JSON_EVENTS + '\'' +
                ", JSON_FLOORS='" + instance.JSON_FLOORS + '\'' +
                ", JSON_HEROES='" + instance.JSON_HEROES + '\'' +
                ", JSON_LOCATIONS='" + instance.JSON_LOCATIONS + '\'' +
                ", JSON_RESOURCES='" + instance.JSON_RESOURCES + '\'' +
                ", JSON_ENFILADES='" + instance.JSON_ENFILADES + '\'' +
                '}';
    }

    private static void printConfigLoaded(long ms, int len) {
        System.out.println(ANSI_GREEN + "[CONFIG LOADER]: Successfully loaded " + len + " strings of config in " + (System.currentTimeMillis() - ms ) + " ms" + ANSI_RESET);
    }
    private static void printConfigNotLoaded(Exception e) {
        System.out.println(ANSI_RED + "[CONFIG LOADER]: Couldn't load config... using default options" + ANSI_RESET);
        System.out.println(e.getMessage());
    }

    private static void printConfigNotValidated(List<String> lines) {
        int len = lines.size();
        System.out.println(ANSI_RED + "[CONFIG LOADER]: Missing " + len + " fields in config: " + lines.toString() + ANSI_RESET);
    }

    public int getColumnCount() {
        return getClass().getDeclaredFields().length;
    }

    private List<String> getMissingColumns() {
        List<String> missingColumns = new ArrayList<>();
        for (Field f : getClass().getDeclaredFields()) {
            try {
                if (f.get(this) == null)
                    missingColumns.add(f.getName());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return missingColumns.size() > 0 ? missingColumns : null;
    }
}