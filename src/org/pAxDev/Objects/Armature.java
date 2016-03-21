package org.pAxDev.Objects;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector4f;

/**
 * Created by Lance on 3/12/2016.
 */
public class Armature {
    private  Armature parent;
    private Joint start;
    private Joint end;
    private float angle = 0.1f;
    private float length = 10;


    public Armature(Joint start, float length, Armature parent) {
        this.start = start;
        this.length = length;
        this.parent = parent;

        if (parent != null) {
            setStart(getParent().getEnd());
        }
        end = new Joint(start.getX()+length,start.getY()+length);

    }

    public void update() {
        render();
    }

    private void render() {
       // drag(Mouse.getX(),Mouse.getY());

        upDateRotation();
        start.draw();
        end.draw();
    }

    public void upDateRotation() {
        end.setX(start.getX() + (float)Math.cos(angle)*length);
        end.setY(start.getY() + (float)Math.sin(angle)*length);
    }
    public void pointTp(float x, float y) {
        float dx = x - start.getX();
        float dy = y - start.getY();
        this.angle = (float)Math.atan2(dy,dx);
    }
    public void drag(float x, float y) {
          this.pointTp(x,y);
          start.setX(x - (float)Math.cos(angle)*length);
          start.setY(y - (float)Math.sin(angle)*length);
        if(getParent() != null) {
            this.parent.drag(getStart().getX(),getStart().getY());
        }
    }

    public Armature getParent() {
        return parent;
    }

    public void setParent(Armature parent) {
        this.parent = parent;
    }

    public Joint getStart() {
        return start;
    }

    public void setStart(Joint start) {
        this.start = start;
    }

    public Joint getEnd() {
        return end;
    }

    public void setEnd(Joint end) {
        this.end = end;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getAngle() {
        return angle;
    }
}
