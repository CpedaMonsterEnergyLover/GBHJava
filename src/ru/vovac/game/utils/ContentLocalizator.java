package ru.vovac.game.utils;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.type.MapType;
import ru.vovac.game.config.Config;
import ru.vovac.game.objects.classes.LocalizableObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ContentLocalizator<T extends LocalizableObject> {
    public static final Path langsPath = Path.of("json/lang");
    public static final Path objectsPath = Path.of("objects");
    public static final Path interfacePath = Path.of("interface");
    private HashMap<Integer, T> map;
    private Path path;

    public ContentLocalizator(HashMap<Integer, T> map, Path path) {
        this.map = map;
        this.path = path;
    }

    public HashMap<Integer, T> localizeCollection() {
        if (map.size() > 0) initLocalDirs(path.toString());
        // Check for new collection elements to add localization fields
        supplyLocalizationFiles();
        // Finally set localized fields of the elements to their file value
        HashMap<Integer, T> updatedCollection = updateLocalizableFields();
        return updatedCollection;
    }

    private HashMap<Integer, T> updateLocalizableFields() {
        String currentLanguage = Config.CURRENT_LANG();
        HashMap<Integer, JsonNode> collectionLocalization = getCollectionLocalization(path, currentLanguage);
        // For every element in existing collection
        for (Integer key : map.keySet()) {
            // Saves the element
            T element = map.get(key);
            JsonNode fieldsToUpdate = collectionLocalization.get(key);
            // Iterates over fields to Update
            Iterator<String> fieldNames = fieldsToUpdate.fieldNames();
            while(fieldNames.hasNext()) {
                // Gets the name of the updating field
                String fieldName = fieldNames.next();
                // Gets the value of the updating field
                JsonNode fieldValue = fieldsToUpdate.get(fieldName);
                element.setLocalizableField(fieldName, fieldValue.asText());
            }
            map.put(key, element);
        }
        return map;
    }

    private void supplyLocalizationFiles() {
        /* Gets map keys, then checks localization file for these keys
            if there are keys in a new collection that haven't been
            added to loc. file yet, deserializes loc. file, add new
            keys and serialize it with new keys.
            Since the localizable content in every collection is the same
            for each collection element, it reads localizedFields from
            the very first element and applies it to all new created elements
        */
        // Reads key set of the existing collection
        List<Integer> keys = new ArrayList<>(map.keySet());
        // Reads localizedFields from the first element of the collection
        List<String> localizedFields = new ArrayList<>(map.get(keys.get(0)).getLocalizableFields().keySet());
        // Creates a JsonNode with localized fields fill with "unlocalized"
        JsonNode localizedFieldsNode = createLocalizationNode(localizedFields);
        // Reads availableLanguages
        List<String> availableLanguages = Config.AVAILABLE_LANGUAGES();
        // For each available language
        for (String s : availableLanguages) {
            // It gets already existing collection localization
            HashMap<Integer, JsonNode> collectionLocalization = getCollectionLocalization(path, s);
            // for each key in a new collection
            for (Integer key : keys) {
                // Checks whether or not already existing collection has that key
                // If not
                if (!collectionLocalization.containsKey(key)) {
                    // put in there new Json node
                    collectionLocalization.put(key, localizedFieldsNode);
                    System.out.println("[LOCALIZATION SUPPLY] [" + s + "/" + path + "] Added new item with id " + key);
                }
            }
            // Write new collection into file
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            try {
                Path finalPath = Path.of(langsPath.toString(), s, objectsPath.toString(), path.toString());
                writer.writeValue(finalPath.toFile(), collectionLocalization);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static JsonNode createLocalizationNode (List<String> fields) {
        StringBuilder localizedFieldsJSONString = new StringBuilder();
        localizedFieldsJSONString.append("{");
        for (int i = 0; i < fields.size(); i++) {
            localizedFieldsJSONString.append("\"").append(fields.get(i)).append("\": \"unlocalized\"");
            if (i + 1 != fields.size()) localizedFieldsJSONString.append(",");
        }
        localizedFieldsJSONString.append("}");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(localizedFieldsJSONString.toString());
            return jsonNode;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static HashMap<Integer, JsonNode> getCollectionLocalization (Path path, String lang){
        Path finalPath = Path.of(langsPath.toString(), lang, objectsPath.toString(), path.toString());
        ObjectMapper mapper = new ObjectMapper();
        String json;
        try {
            json = new String(Files.readAllBytes(Paths.get(finalPath.toString())));
            // If no objects has been added yet
            if (json.length() == 0) {
                return new HashMap<>();
            }
            MapType mapType = mapper.getTypeFactory().constructMapType(Map.class, Integer.class, JsonNode.class);
            HashMap<Integer, JsonNode> loaded = mapper.readValue(json, mapType);
            if (loaded == null) return new HashMap<>();
            return loaded;
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    public static void initLocalDirs(String res) {
        createLangDirs();
        createCollectionLangFile(res);
    }

    private static void createLangDirs() {
        List<String> languages = Config.AVAILABLE_LANGUAGES();
        for (String s : languages) {
            File langDir = new File(Path.of(langsPath.toString(), s).toString());
            if (!langDir.exists()) {
                langDir.mkdir();
            }
            createLangSubDirs(langDir.toString());
        }
    }

    private static void createLangSubDirs(String dir) {
        File langObjectsDir = new File(Path.of(dir, objectsPath.toString()).toString());
        File langInterfaceDir = new File(Path.of(dir, interfacePath.toString()).toString());
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
                    System.out.println("[LOCALIZATOR] Created " + collectionFile.toString() + " in " + (System.currentTimeMillis() - ms) + " ms");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
