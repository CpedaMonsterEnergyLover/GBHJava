package ru.vovac.game.objects.classes;

import java.util.HashMap;

public class LocalizableObject {
    private HashMap<String, String> localizableFields = new HashMap<>();

    public HashMap<String, String> getLocalizableFields() {
        return localizableFields;
    }

    public void addLocalizableField(String field) {
        this.localizableFields.put(field, "");
    }

    public void setLocalizableField(String field, String value) { this.localizableFields.put(field, value); }

    public String getLocalizableField(String key) { return this.localizableFields.get(key); }
}
