package ru.vovac.game.scene;

import com.jme3.asset.AssetManager;
import com.jme3.asset.plugins.FileLocator;
import com.jme3.light.DirectionalLight;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import ru.vovac.application.MainApplication;
import ru.vovac.game.objects.Floor;

import static main.java.CollectionLoader.floorCollection;
import static main.java.CollectionLoader.locationCollection;

public class GameFieldBuilder {
    private AssetManager assetManager;
    private Node rootNode;
    private int width;
    private int height;
    private int floorID;

    public GameFieldBuilder(MainApplication application, int width, int height, int floorID) {
        this.assetManager = application.getAssetManager();
        this.rootNode = application.getRootNode();
        this.width = width;
        this.height = height;
        this.floorID = floorID;
    }

    public void buildTest() {
        // Create all cards holder
        Node fieldPivot = new Node("fieldPivot");
        float distance = 0.025f;
        // Load a model
        Spatial card = assetManager.loadModel("Models/card/card.j3o");
        card.rotate(FastMath.DEG_TO_RAD * 90, FastMath.DEG_TO_RAD * 180, 0.0f);
        card.setMaterial( assetManager.loadMaterial("Materials/TestMaterial.j3m"));

        // Get floor id
        Floor floor = floorCollection.get(floorID);
        // Sort its rarities
        floor.sortRarities();
        for (int i = 0; i < width ; i++) {
            for (int j = 0; j < height; j++) {
                Spatial clonedCard = card.clone();
                clonedCard.setName("card_" + i + "_" + j);
                clonedCard.setUserData("location", locationCollection.get(4));
                clonedCard.setUserData("x", i);
                clonedCard.setUserData("y", j);
                System.out.println("CARD " + clonedCard.getName() + " ADDED " + clonedCard.getUserData("location"));
                fieldPivot.attachChild(clonedCard);
                clonedCard.move((2.0f + distance) * i, (3.0f) * j,0.0f);
            }
        }

        rootNode.attachChild(fieldPivot);
        // You must add a directional light to make the model visible!
        setLight();
    }

    private void setLight() {
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f).normalizeLocal());
        rootNode.addLight(sun);
    }
}
