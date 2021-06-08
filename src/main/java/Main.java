package main.java;

import ru.vovac.game.config.Config;

public class Main {
    public static void main(String[] args) {
        Config.load();
        CollectionLoader.loadAll();

        //System.out.println(locationCollection.size());
        //System.out.println(eventCollection.get(1).newGameObject());
    }
}
