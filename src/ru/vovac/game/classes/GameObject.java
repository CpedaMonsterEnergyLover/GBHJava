package ru.vovac.game.classes;

public class GameObject extends LocalizableObject {

    protected static int idCounter = 0;
    protected final int objectID = ++idCounter;

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
