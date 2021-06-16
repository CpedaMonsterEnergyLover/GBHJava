package main.java;

import org.apache.commons.lang3.ArrayUtils;
import ru.vovac.application.MainApplication;
import ru.vovac.game.config.Config;
import ru.vovac.wrappers.IntegerIntegerHashMapWrapper;

import java.sql.SQLOutput;
import java.util.HashMap;

import static main.java.CollectionLoader.locationCollection;


public class Main {
    public static void main(String[] args) {
        Config.load();
        CollectionLoader.loadAll();
        MainApplication.main(new String[]{""});
    }
}
