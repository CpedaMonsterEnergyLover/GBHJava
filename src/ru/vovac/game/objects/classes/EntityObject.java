package ru.vovac.game.objects.classes;

public class EntityObject extends LocalizableObject{
    private int idCounter = 0;
    private final int objectID = ++idCounter;
    private String title;
    private int maxHealth;
    private int currentHealth;
    private int maxAttack;
    private int currentAttack;
    private int maxDefense;
    private int currentDefense;
    private int maxEnergy;
    private int currentEnergy;


    public int getObjectID() {
        return objectID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getMaxAttack() {
        return maxAttack;
    }

    public void setMaxAttack(int maxAttack) {
        this.maxAttack = maxAttack;
    }

    public int getCurrentAttack() {
        return currentAttack;
    }

    public void setCurrentAttack(int currentAttack) {
        this.currentAttack = currentAttack;
    }

    public int getMaxDefense() {
        return maxDefense;
    }

    public void setMaxDefense(int maxDefense) {
        this.maxDefense = maxDefense;
    }

    public int getCurrentDefense() {
        return currentDefense;
    }

    public void setCurrentDefense(int currentDefense) {
        this.currentDefense = currentDefense;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public void setCurrentEnergy(int currentEnergy) {
        this.currentEnergy = currentEnergy;
    }
}
