package ru.vovac.game.objects.classes;

public class GameObject extends LocalizableObject {
    private static int idCounter = 0;
    private final int objectID = ++idCounter;

    public int getObjectID() {
        return objectID;
    }

    @Override
    public String toString() {
        return "GameObject{" +
                "id=" + objectID +
                '}';
    }
}
