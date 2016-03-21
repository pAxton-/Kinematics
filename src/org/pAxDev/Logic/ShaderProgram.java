package org.pAxDev.Logic;

import org.lwjgl.util.vector.Vector3f;
import org.pAxDev.Maths.Matrix4f;
import org.pAxDev.Util.ShaderUtils;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;

/**
 * Created by Lance on 3/19/2016.
 */
public class ShaderProgram {
    public static ShaderProgram LIGHT;
    private final int ID;
    public static final int VERTEX_ATTRIB = 0;
    public static final int TCOORD_ATTRIB = 1;



    private boolean enabled = false;


    private Map<String, Integer> locationCache = new HashMap<String, Integer>();



    public ShaderProgram(String vertex, String fragment) {
        ID = ShaderUtils.load(vertex, fragment);
        load();
    }

    public void load() {
        LIGHT = new ShaderProgram("src/org/pAxDev/Shaders/shader.vert", "src/org/pAxDev/Shaders/shader.frag");
    }
    public int getUniform(String name) {
        if (locationCache.containsKey(name))
            return locationCache.get(name);

        int result = glGetUniformLocation(ID, name);
        if (result == -1)
            System.err.println("Could not find uniform variable '" + name + "'!");
        else
            locationCache.put(name, result);
        return result;
    }

    public void setUniform1i(String name, int value) {
        if (!enabled) enable();
        glUniform1i(getUniform(name), value);
    }

    public void setUniform1f(String name, float value) {
        if (!enabled) enable();
        glUniform1f(getUniform(name), value);
    }

    public void setUniform2f(String name, float x, float y) {
        if (!enabled) enable();
        glUniform2f(getUniform(name), x, y);
    }

    public void setUniform3f(String name, Vector3f vector) {
        if (!enabled) enable();
        glUniform3f(getUniform(name), vector.x, vector.y, vector.z);
    }

    public void setUniformMat4f(String name, Matrix4f matrix) {
        if (!enabled) enable();
        glUniformMatrix4(getUniform(name), false, matrix.toFloatBuffer());
    }

    public void enable() {
        glUseProgram(ID);
        enabled = true;
    }

    public void disable() {
        glUseProgram(0);
        enabled = false;
    }

}

