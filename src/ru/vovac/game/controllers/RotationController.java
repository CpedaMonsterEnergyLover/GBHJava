package ru.vovac.game.controllers;

import com.jme3.export.Savable;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import ru.vovac.game.utils.Utils;

import java.util.Random;

public class RotationController extends AbstractControl implements Savable, Cloneable{
    private boolean opened = false;
    private boolean clicked = false;
    private boolean rotating = false;
    private final float rotatingTime  = 1000;
    private long rotatingTimeStart = 0;
    private float currentAngle;
    private float currentToss;
    private int rotationDir;

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    @Override
    protected void controlUpdate(float tpf) {
        /*if (clicked && !opened) {
            opened = true;
            rotating = true;
            currentAngle = 0;
            currentToss = 0;
            rotatingTimeStart = System.currentTimeMillis();
        }
        if (rotating) {
            float angle = (180 - currentAngle) / rotatingTime;
            spatial.rotate(0f, 0f, angle * FastMath.DEG_TO_RAD);
            currentAngle += angle;
            float toss = (1f - currentToss) / rotatingTime;
            float dir = 1f;
            System.out.println(System.currentTimeMillis() - rotatingTimeStart + " tpf:" + tpf);
            //if (System.currentTimeMillis() - rotatingTimeStart > 325) dir = -1f;
            spatial.move(new Vector3f(0f, 0.03f * dir, 0f));
            currentToss += toss;
            if (currentAngle > 178){
                rotating = false;
                Quaternion roll180 = new Quaternion();
                spatial.setLocalRotation(roll180.fromAngleAxis(FastMath.PI, new Vector3f(0, 0, 1)));
            }
        }*/
        if (clicked && !opened) {
            opened = true;
            rotating = true;
            currentAngle = 0;
            currentToss = 0;
            rotatingTimeStart = System.currentTimeMillis();
            rotationDir = Utils.randomDir();
        }
        if (rotating) {
            float timeLeft = System.currentTimeMillis() - rotatingTimeStart;
            float currentRotation = timeLeft * (180 / rotatingTime);
            float toss = getTossPosition(timeLeft, rotatingTime, 1f);
            spatial.setLocalTranslation
                    (spatial.getLocalTranslation().x,
                  spatial.getLocalTranslation().y,
                     toss);
            Quaternion roll = new Quaternion();
            spatial.getLocalRotation().fromAngles
                    (new float[]{
                            FastMath.PI / 2,
                            rotationDir * (FastMath.DEG_TO_RAD * currentRotation),
                            0});
            if (System.currentTimeMillis() - rotatingTimeStart >= rotatingTime){
                rotating = false;
                Quaternion roll180 = new Quaternion();
                spatial.setLocalRotation(roll180.fromAngleAxis(FastMath.PI, new Vector3f(0, -1, 1)));
            }
        }
    }

    private float getTossPosition(float timeLeft, float rotatingTime, float distance) {
        float result = (float) (-1 * Math.pow(timeLeft - Math.sqrt(rotatingTime * 250), 2) / (rotatingTime * 250) * distance + distance);
        if(timeLeft >= rotatingTime / 2) result = Utils.ensureRange(result, 0.2f, distance);
        return result;
    }

    @Override
    protected void controlRender(RenderManager renderManager, ViewPort viewPort) {

    }

    @Override
    public void setSpatial(Spatial spatial) {
        super.setSpatial(spatial);
        /* Example:
        if (spatial != null){
            // initialize
        }else{
            // cleanup
        }
        */
    }

    @Override
    public RotationController cloneForSpatial(Spatial spatial){
        final RotationController control = new RotationController();
        /* Optional: use setters to copy userdata into the cloned control */
        // control.setIndex(i); // example
        control.setSpatial(spatial);
        return control;
    }
}
