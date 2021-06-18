package main.java;

import ru.vovac.application.MainApplication;
import ru.vovac.game.config.Config;

import static main.java.CollectionLoader.locationCollection;


public class Main {
    public static void main(String[] args) {
        Config.load();
        CollectionLoader.loadAll();
        MainApplication.main(new String[]{""});
        locationCollection.get(1).getObjectID();
    }
}
