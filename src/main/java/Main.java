package main.java;

import com.fasterxml.jackson.databind.JsonNode;
import ru.vovac.game.config.Config;
import ru.vovac.game.objects.Location;
import ru.vovac.game.objects.classes.GameObject;
import ru.vovac.game.utils.ContentLocalizator;
import ru.vovac.game.utils.JSONContentLoader;
import ru.vovac.game.utils.LangsEnum;
import ru.vovac.game.utils.Utils;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static main.java.CollectionLoader.*;

public class Main {
    public static void main(String[] args) {
        Config.load();
        CollectionLoader.loadAll();
    }
}
