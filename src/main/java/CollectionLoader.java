package main.java;

import ru.vovac.game.config.Config;
import ru.vovac.game.objects.*;
import ru.vovac.game.utils.JSONContentLoader;

import java.util.HashMap;

public class CollectionLoader {
    public static HashMap<Integer, Location> locationCollection;
    public static HashMap<Integer, Creature> creatureCollection;
    public static HashMap<Integer, Event> eventCollection;
    public static HashMap<Integer, Hero> heroCollection;
    public static HashMap<Integer, Resource> resourceCollection;
    public static HashMap<Integer, Floor> floorCollection;
    public static HashMap<Integer, Enfilade> enfiladeCollection;


    public static void loadAll() {
        JSONContentLoader<Location> locationJSONContentLoader = new JSONContentLoader<>(Config.JSON_LOCATIONS(), Location.class);
        JSONContentLoader<Event> eventJSONContentLoader = new JSONContentLoader<>(Config.JSON_EVENTS(), Event.class);
        JSONContentLoader<Resource> resourceJSONContentLoader = new JSONContentLoader<>(Config.JSON_RESOURCES(), Resource.class);
        JSONContentLoader<Creature> creatureJSONContentLoader = new JSONContentLoader<>(Config.JSON_CREATURES(), Creature.class);
        JSONContentLoader<Hero> heroJSONContentLoader = new JSONContentLoader<>(Config.JSON_HEROES(), Hero.class);
        JSONContentLoader<Floor> floorJSONContentLoader= new JSONContentLoader<>(Config.JSON_FLOORS(), Floor.class);
        JSONContentLoader<Enfilade> enfiladeJSONContentLoader = new JSONContentLoader<>(Config.JSON_ENFILADES(), Enfilade.class);
        locationCollection = locationJSONContentLoader.loadContent();
        creatureCollection = creatureJSONContentLoader.loadContent();
        eventCollection = eventJSONContentLoader.loadContent();
        resourceCollection = resourceJSONContentLoader.loadContent();
        heroCollection = heroJSONContentLoader.loadContent();
        floorCollection = floorJSONContentLoader.loadContent();
        enfiladeCollection = enfiladeJSONContentLoader.loadContent();
    }
}
