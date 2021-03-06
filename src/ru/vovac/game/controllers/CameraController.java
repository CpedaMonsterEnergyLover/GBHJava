package ru.vovac.game.controllers;

import com.jme3.collision.CollisionResults;
import com.jme3.input.FlyByCamera;
import com.jme3.input.InputManager;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import ru.vovac.application.MainApplication;
import ru.vovac.game.utils.Utils;

public class CameraController {
    private final Camera camera;
    private final FlyByCamera flyCamera;
    private final InputManager inputManager;
    private final Node rootNode;
    private int clickTimer = 0;
    private Vector2f cursorPosition;
    private boolean pickedField = false;
    boolean caughtPoint = false;
    private float dragRatio;
    private Vector3f pt = null;
    private Vector3f fieldPivot = null;
    private Vector2f pickedPosition;
    //TODO: these values might be configurable
    private final int clickAllowed = 10;
    private final float cursorTooFar = 50;

    private final ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean keyPressed, float tpf) {
            if (name.equals("Mouse click") && keyPressed){
                pickedField = true;
                pickedPosition = inputManager.getCursorPosition().clone();
            }
            if (name.equals("Mouse click") && !keyPressed) {
                pickedField = false;
                if (clickTimer <= clickAllowed) updateClickedCard();
                clickTimer = 0;
                caughtPoint = false;
            }
            if (name.equals("Wheel rotate UP")) {
                if (camera.getLocation().getZ() > 6.7)
                    moveCamera(0, 0, -0.65f);
                dragRatio = calculateDragRatio();
            }
            if (name.equals("Wheel rotate DOWN")) {
                if (camera.getLocation().getZ() < 29.5)
                    moveCamera(0, 0, 0.65f);
                dragRatio = calculateDragRatio();
            }
        }
    };

    public CameraController(MainApplication app) {
        this.camera = app.getCamera();
        this.flyCamera = app.getFlyByCamera();
        this.inputManager = app.getInputManager();
        this.rootNode = app.getRootNode();
    }

    public void init() {
        flyCamera.setEnabled(false);
        inputManager.addListener(actionListener, "Mouse click", "Wheel rotate UP", "Wheel rotate DOWN");
        inputManager.addMapping("Mouse click",
                new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addMapping("Wheel rotate UP",
                new MouseAxisTrigger(MouseInput.AXIS_WHEEL,false));
        inputManager.addMapping("Wheel rotate DOWN",
                new MouseAxisTrigger(MouseInput.AXIS_WHEEL,true));
        camera.setLocation(new Vector3f(0, 0, 24));
        fieldPivot = rootNode.getChild("fieldPivot").getLocalTranslation().clone();
        //TODO: recalculate dragratio after window rescale
        dragRatio = calculateDragRatio();
    }

    public void moveCamera(float x, float y, float z){
        camera.setLocation(new Vector3f(x, y, z).add(camera.getLocation()));
    }

    public void updateListener() {
        // Field drag & move system (do not touch it please)
        cursorPosition = inputManager.getCursorPosition();
        Vector2f centeredPos = centeredCursorPosition();
        if (pickedField) {
            clickTimer++;
            boolean mouseTooFar = Math.abs(pickedPosition.x - cursorPosition.x) > cursorTooFar ||
                    Math.abs(pickedPosition.y - cursorPosition.y) > cursorTooFar;
            if (clickTimer == clickAllowed || (mouseTooFar && !caughtPoint)) {
                calculateDragPoint();
                caughtPoint = true;
            }
            if (clickTimer > clickAllowed || caughtPoint) {
                // Start moving field
                assert pt != null;
                float xx = pt.getX() - fieldPivot.getX();
                float yy = pt.getY() - fieldPivot.getY();
                float ww = MainApplication.gameFieldBuilder.width * 2 - 1f;
                float hh = MainApplication.gameFieldBuilder.height * 3 - 1.5f;
                //TODO: REWRITE WITH GETTERS
                rootNode.getChild("fieldPivot").setLocalTranslation(new Vector3f(
                        Utils.ensureRange(centeredPos.getX() * dragRatio - xx, -ww, 0),
                        Utils.ensureRange(centeredPos.getY() * dragRatio - yy, -hh, 0),
                        0
                ));
            }
        }
    }
    private float calculateDragRatio(){
        float dragRatio = 0;
        // Gets first point to calculate ratio - center of the screen
        Vector3f firstCollisionPoint = null;
        Vector3f secondCollisionPoint = null;
        CollisionResults pointResults = new CollisionResults();
        Ray pointRay = new Ray(camera.getLocation(), camera.getDirection());
        rootNode.getChild("ghostTable").collideWith(pointRay, pointResults);
        if (pointResults.size() > 0)
            firstCollisionPoint = pointResults.getClosestCollision().getContactPoint();
        // Then gets second point - center of the screen + 10px -> X axis
        Vector2f screenCentre = new Vector2f((float) camera.getWidth() / 2, (float) camera.getHeight() / 2);
        Vector2f secondPoint = new Vector2f(screenCentre.getX() + 20, screenCentre.getY());
        Vector3f secondPoint3d = camera.getWorldCoordinates(secondPoint, 0f).clone();
        Vector3f dir = camera.getWorldCoordinates(new Vector2f(secondPoint.x, secondPoint.y), 1f).subtractLocal(secondPoint3d).normalizeLocal();
        pointRay = new Ray(secondPoint3d, dir);
        rootNode.getChild("ghostTable").collideWith(pointRay, pointResults);
        if (pointResults.size() > 0)
            secondCollisionPoint = pointResults.getClosestCollision().getContactPoint();
        // Uses these points to calculate dragRatio
        if (firstCollisionPoint != null && secondCollisionPoint != null) {
            float firstX = firstCollisionPoint.getX();
            float secondX = secondCollisionPoint.getX();
            dragRatio = (secondX - firstX) / 20;
        }
        if (dragRatio==0) System.out.println("WARNING!!!!! COULDN'T CALCULATE DRAG RATIO!!!");
        return dragRatio;
    }

    private Vector2f centeredCursorPosition() {
        float w = camera.getWidth();
        float h = camera.getHeight();
        return new Vector2f(cursorPosition.getX() - w / 2, cursorPosition.getY() - h / 2);
    }

    private void calculateDragPoint() {
        Ray ray = getCollisionRay();
        CollisionResults results = new CollisionResults();
        // Collect intersections between ray and all nodes in results list.
        rootNode.collideWith(ray, results);
        if (results .size() > 0) {
            pt = results.getClosestCollision().getContactPoint();
            fieldPivot = rootNode.getChild("fieldPivot").getLocalTranslation().clone();
        }
    }

    private Ray getCollisionRay() {
        // Convert screen click to 3d position
        Vector2f click2d = cursorPosition;
        Vector3f click3d = camera.getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 0f).clone();
        Vector3f dir = camera.getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 1f).subtractLocal(click3d).normalizeLocal();
        // Aim the ray from the clicked spot forwards.
        return new Ray(click3d, dir);
    }

    private void updateClickedCard(){
        Ray ray = getCollisionRay();
        CollisionResults results = new CollisionResults();
        Spatial a = rootNode.getChild("fieldPivot");
        a.collideWith(ray, results);
        if (results.size() > 0){
            Geometry hit = results.getClosestCollision().getGeometry();
            String name = hit.getName();
            if (name.contains("cardBox")) {
                Spatial cardPivot = hit.getParent();
                cardPivot.getControl(RotationController.class).setClicked(true);
            }
        }
    }
}
