package org.pAxDev.Objects;

import org.lwjgl.util.vector.Vector3f;
import org.pAxDev.Logic.ShaderProgram;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;


/**
 * Created by Lance on 3/19/2016.
 */
public class Token extends Joint{
    ShaderProgram shader = new ShaderProgram("src/org/pAxDev/Shaders/shader.vert", "src/org/pAxDev/Shaders/shader.frag");

    public Token(float x, float y) {
        super(x, y);
        // new ShaderProgram("src/org/pAxDev/Shaders/shader.vert", "Shaders/shader.frag");
        setX(100);

        setY(100);

    }

    public void draw() {





        glDisable(GL_TEXTURE_2D);
        glPushMatrix();
        shader.enable();
        shader.setUniform2f("lightLocation", getX(), getY());
        shader.setUniform3f("lightColor", new Vector3f(1,0,0));
        shader.setUniform1f("screenHeight", 768);
        glTranslatef(getX(), getY(), 0);
        glRotatef(0, 0, 0, 1);
        glPointSize(4);

       // glColor4f((float)Math.cos(angle)*.625f, (float)Math.sin(angle)*0.625f, (float)Math.sin(angle)*.625f, 1);
        glBegin(GL_POINTS);
        glVertex3f(0.0f,0.0f,0.0f);
        glEnd();
        glPopMatrix();
        angle = angle - .15f;
        shader.disable();
    }
}
