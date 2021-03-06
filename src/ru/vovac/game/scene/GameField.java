package ru.vovac.game.scene;

import com.jme3.asset.AssetManager;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import org.apache.commons.lang3.tuple.MutablePair;
import ru.vovac.application.MainApplication;
import ru.vovac.game.controllers.RotationController;
import ru.vovac.game.objects.Floor;
import ru.vovac.game.objects.Location;

import java.util.*;

import static main.java.CollectionLoader.floorCollection;
import static main.java.CollectionLoader.locationCollection;

public class GameField {
    private final AssetManager assetManager;
    private final Node rootNode;
    public final int width;
    public final int height;
    private final Floor floor;
    private Node fieldPivot;
    public int rareOdds = 32;
    public int epicOdds = 5;
    public int commonsCounter = 0;
    public int raresCounter = 0;
    public int epicsCounter = 0;

    public GameField(MainApplication application, int width, int height, int floorID) {
        this.assetManager = application.getAssetManager();
        this.rootNode = application.getRootNode();
        this.width = width;
        this.height = height;
        this.floor = floorCollection.get(floorID);
    }

    public void init() {
        generateGameField();
        createGhostTable();
    }

    private void generateGameField() {
        // Creates all cards holder
        fieldPivot = new Node("fieldPivot");
        fieldPivot.move(0, 0, 0);
        // Loads models
        Material testMaterial = assetManager.loadMaterial("Materials/TestMaterial.j3m");
        Spatial card = assetManager.loadModel("Models/card/card.j3o");
        card.setMaterial(testMaterial);
        Spatial frame_01 = assetManager.loadModel("Models/cardframe_bricks01/cardframe_bricks01.j3o");
        frame_01.setMaterial(testMaterial);
        Spatial frame_02 = assetManager.loadModel("Models/cardframe_bricks02/cardframe_bricks02.j3o");
        frame_02.setMaterial(testMaterial);
        // Creates a list of loaded card frames
        List<Spatial> cardFrameList = new ArrayList<>();
        cardFrameList.add(frame_01);
        cardFrameList.add(frame_02);

        // Sort floor rarities
        floor.sortRarities();

        // It must to generate coords for story cards first
        // For that, initiates a Pair array
        HashMap<MutablePair<Integer, Integer>, Integer> storyCoords = new HashMap<>();
        // For each story location, generates a hashmap with keys as pair of unique coords
        for (Integer id : floor.getStoryLocations()) {
            int x = new Random().nextInt(width);
            int y = new Random().nextInt(height);
            // If these coords are already used, rerolls them
            while (storyCoords.containsKey(new MutablePair<>(x, y))) {
                x = new Random().nextInt(width);
                y = new Random().nextInt(height);
            }
            storyCoords.put(new MutablePair<>(x, y), id);
        }
        Location cardLocation;
        // For cycle, iterates over each floor cell
        for (int i = 0; i < width ; i++) {
            for (int j = 0; j < height; j++) {
                // Clones card model
                Spatial clonedCard = card.clone();
                clonedCard.setName("card");
                clonedCard.setUserData("hui", "" + i + j);

                // Creates a card pivot
                Node cardPivot = new Node("cardPivot");
                // Chooses random frame spatial from the list
                Spatial cardFrame = cardFrameList.get(new Random().nextInt(cardFrameList.size())).clone();
                cardFrame.setName("frame");

                // Creates a card box for ray picking...
                Geometry cardBox = new Geometry("cardBox_" + i + "_" + j, new Box(1, 1.5f, 0.001f));
                cardBox.setCullHint(Spatial.CullHint.Always);
                cardBox.setMaterial(testMaterial);
                cardBox.move(0f, 0.23f, 0f);
                cardBox.rotate(FastMath.DEG_TO_RAD * 90, /*FastMath.DEG_TO_RAD * 180*/ 0f, 0f);
                cardBox.setUserData("x", i);
                cardBox.setUserData("y", j);

                // Adds everything to the pivot
                cardPivot.attachChild(cardBox);
                cardPivot.attachChild(clonedCard);
                cardPivot.attachChild(cardFrame);
                cardPivot.addControl(new RotationController());
                cardPivot.rotate(FastMath.DEG_TO_RAD * 90, /*FastMath.DEG_TO_RAD * 180*/ 0f, 0f);
                cardPivot.move((2.0f) * i, (3.0f) * j,0.0f);
                // Attaches card pivot to the field
                fieldPivot.attachChild(cardPivot);
                // Randomizing locations
                // Check whether this cell is for story card or not
                if (storyCoords.containsKey(new MutablePair<>(i, j))) {
                    cardLocation = locationCollection.get(storyCoords.get(new MutablePair<>(i, j)));
                    clonedCard.setUserData("opened", true);
                    if (cardLocation.getLocationID() == 1) {
                        cardPivot.rotate(0f, 0f, FastMath.DEG_TO_RAD * 180);
                        System.out.println("PORTAL");
                    }
                } else {
                    int rnd = new Random().nextInt(100);
                    if (rnd > rareOdds + epicOdds) {
                        commonsCounter++;
                        cardLocation = locationCollection.get(floor.getCommonLocations().get(new Random().nextInt
                                (floor.getCommonLocations().size()))).newGameObject();
                    } else if (epicOdds < rnd && (epicOdds + rareOdds) > rnd) {
                        raresCounter++;
                        cardLocation = locationCollection.get(floor.getRareLocations().get(new Random().nextInt
                                (floor.getRareLocations().size()))).newGameObject();
                    } else {
                        epicsCounter++;
                        cardLocation = locationCollection.get(floor.getEpicLocations().get(new Random().nextInt
                                (floor.getEpicLocations().size()))).newGameObject();
                    }
                }
                cardBox.setUserData("location", cardLocation);
                //System.out.println(i + ":" + j + " ADDED " + cardLocation.getStringID() + " AS OBJECT " + cardLocation.getObjectID());
            }
        }

        rootNode.attachChild(fieldPivot);
        rootNode.getChild("fieldPivot").move((float) - width + 1, (float) - height * 1.5f + 1.5f, 0f);
        // You must add a directional light to make the model visible!
        setLight();

        int fieldSize = width * height;
        System.out.println("commons percent: " + ((double)commonsCounter / fieldSize) * 100  + "%");
        System.out.println("rares percent: "+ ((double) raresCounter / fieldSize) * 100  + "%");
        System.out.println("epics percent: "+  ((double) epicsCounter / fieldSize) * 100 + "%");
    }

    private void createGhostTable() {
        Geometry ghostTable = new Geometry("ghostTable", new Box(width * 2 + 20, height * 3 + 20, 0.1f));
        Material mat = assetManager.loadMaterial("Materials/TestMaterial.j3m");
        ghostTable.setCullHint(Spatial.CullHint.Always);
        ghostTable.setMaterial(mat);
        ghostTable.setLocalTranslation(width + 10, height + 10, 0.1f);
        fieldPivot.attachChild(ghostTable);
    }

    private void setLight() {
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f).normalizeLocal());
        rootNode.addLight(sun);
    }
}
