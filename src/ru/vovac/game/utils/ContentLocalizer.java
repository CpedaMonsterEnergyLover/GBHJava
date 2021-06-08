package ru.vovac.game.utils;

import ru.vovac.game.config.Config;
import ru.vovac.game.objects.classes.GameObject;
import ru.vovac.game.objects.classes.LocalizableObject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContentLocalizer {
    public static final Path langsPath = Path.of("json/lang");
    public static final Path objectsPath = Path.of("objects");
    public static final Path interfacePath = Path.of("interface");

    public static boolean lookForLangDir(String directory) {
        Path path = Paths.get(langsPath.toString() + directory);
        return Files.exists(path);
    }
    /* Для каждой загруженной коллекции ресурса проверяет, есть ли соотв. файл в /lang/NN/, где NN - папка языка
    * Доступные языки прописаны в конфиге.
    * Если такой папки нет, то он ее создает. Затем если файл найден, то возможно перевод уже есть, тогда он
    * десериализует имеющийся файл и смотрит есть ли в нем загруженные. Те, которых нет, он добавляет и сериализует опять.
    *
    *
    * */
    public static void initLocalDirs(String res) {
        createLangDirs();
        createCollectionLangFile(res);
    }

    private static void createLangDirs() {
        List<String> languages = Config.AVAILABLE_LANGUAGES();
        for (String s : languages) {
            File langDir = new File(langsPath.toString() + "/" + s);
            if (!langDir.exists()) {
                langDir.mkdir();
            }
            createLangSubDirs(langDir.toString());
        }
    }

    private static void createLangSubDirs(String dir) {
        File langObjectsDir = new File(dir + "/objects");
        File langInterfaceDir = new File(dir + "/interface");
        if (!langObjectsDir.exists()) langObjectsDir.mkdir();
        if (!langInterfaceDir.exists()) langInterfaceDir.mkdir();
    }

    private static void createCollectionLangFile (String res) {
        long ms = System.currentTimeMillis();
        List<String> languages = Config.AVAILABLE_LANGUAGES();
        for (String s : languages) {
            File collectionFile = new File(Path.of(langsPath.toString(), s, objectsPath.toString(), res).toString());
            if (!collectionFile.exists()) {
                try {
                    collectionFile.createNewFile();
                    System.out.println("[LOCALIZER] Created " + collectionFile.toString() + " in " + (System.currentTimeMillis() - ms) + " ms");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
