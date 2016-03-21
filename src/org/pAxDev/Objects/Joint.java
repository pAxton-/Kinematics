package org.pAxDev.Objects;

import org.lwjgl.util.vector.Vector4f;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Lance on 3/12/2016.
 */
public class Joint {
    private float x, y;
    private Vector4f color = new Vector4f(.4f,.6f,.1f,1);
    float angle = 0;

    public Joint(float x, float y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }



    public void draw() {

        glDisable(GL_TEXTURE_2D);
        glPushMatrix();
        glTranslatef(x, y, 0);
        glRotatef(0, 0, 0, 1);
        glPointSize(4);

        glColor4f((float)Math.cos(angle)*.625f, (float)Math.sin(angle)*0.625f, (float)Math.sin(angle)*.625f, color.w);
        glBegin(GL_POINTS);
        glVertex3f(0.0f,0.0f,0.0f);
        glEnd();
        glPopMatrix();
        angle = angle - .15f;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Vector4f getColor() {
        return color;
    }

    public void setColor(Vector4f color) {
        this.color = color;
    }
}
