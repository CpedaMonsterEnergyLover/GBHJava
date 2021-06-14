package main.java;

import ru.vovac.game.config.Config;
import ru.vovac.game.objects.*;
import ru.vovac.game.utils.JSONContentLoader;

import java.util.HashMap;

public class CollectionLoader {
    public static HashMap<Integer, Location> locationCollection = null;
    public static HashMap<Integer, Creature> creatureCollection = null;
    public static HashMap<Integer, Event> eventCollection = null;
    public static HashMap<Integer, Resource> resourceCollection = null;
    public static HashMap<Integer, Floor> floorCollection = null;
    public static HashMap<Integer, Enfilade> enfiladeCollection = null;
    public static HashMap<Integer, Dice> diceCollection = null;
    public static HashMap<Integer, Combo> comboCollection = null;
    public static HashMap<Integer, Proc> procCollection = null;


    public static void loadAll() {
        JSONContentLoader<Event> eventJSONContentLoader = new JSONContentLoader<>(Config.JSON_EVENTS(), Event.class);
        eventCollection = eventJSONContentLoader.loadContent();
        JSONContentLoader<Enfilade> enfiladeJSONContentLoader = new JSONContentLoader<>(Config.JSON_ENFILADES(), Enfilade.class);
        enfiladeCollection = enfiladeJSONContentLoader.loadContent();
        JSONContentLoader<Location> locationJSONContentLoader = new JSONContentLoader<>(Config.JSON_LOCATIONS(), Location.class);
        locationCollection = locationJSONContentLoader.loadContent();
        JSONContentLoader<Floor> floorJSONContentLoader= new JSONContentLoader<>(Config.JSON_FLOORS(), Floor.class);
        floorCollection = floorJSONContentLoader.loadContent();
        JSONContentLoader<Resource> resourceJSONContentLoader = new JSONContentLoader<>(Config.JSON_RESOURCES(), Resource.class);
        resourceCollection = resourceJSONContentLoader.loadContent();
        JSONContentLoader<Creature> creatureJSONContentLoader = new JSONContentLoader<>(Config.JSON_CREATURES(), Creature.class);
        creatureCollection = creatureJSONContentLoader.loadContent();

        JSONContentLoader<Dice> diceJSONContentLoader = new JSONContentLoader<>(Config.JSON_DICE(), Dice.class);
        diceCollection = diceJSONContentLoader.loadContent();
        JSONContentLoader<Combo> comboJSONContentLoader = new JSONContentLoader<>(Config.JSON_COMBOS(), Combo.class);
        comboCollection = comboJSONContentLoader.loadContent();
        JSONContentLoader<Proc> procJSONContentLoader = new JSONContentLoader<>(Config.JSON_PROCS(), Proc.class);
        procCollection = procJSONContentLoader.loadContent();
    }
}
