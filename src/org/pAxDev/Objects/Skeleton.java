package org.pAxDev.Objects;

import org.lwjgl.input.Mouse;

import java.util.ArrayList;

/**
 * Created by Lance on 3/12/2016.
 */
public class Skeleton {
    //Armature[] chain;
    ArrayList<Armature> chain = new ArrayList<>();
    private int segments;
    private float segmentLength;

    public Skeleton(int segements, float segmentLength) {
        this.segments = segements;
        this.segmentLength = segmentLength;
       // chain = new Armature[segements];
       // chain[0] = new Armature(new Joint(0,0),segmentLength,null);
        chain.add(0,new Armature(new Joint(0,0),segmentLength,null));
        for(int i = 1; i < segements; i++) {
           // chain[i] = new Armature(new Joint(0,0),segmentLength,chain[i-1]);
            chain.add(new Armature(new Joint(0,0),segmentLength,chain.get(i-1)));
        }
    }

    public void update() {

        for(Armature arm : chain) {
           // if(chain[segments-1] == arm) {
            if(chain.get(chain.size()-1) == arm) {
                arm.drag(Mouse.getX(), Mouse.getY());
                System.out.println(chain.size());

            }


            arm.update();
        }

    }

    public void addArm() {
        int last = chain.size();
        chain.add(new Armature(new Joint(0,0),segmentLength,chain.get(last-1)));


    }
    /*
    public Joint[] getAllJoints() {
        Joint[] list = new Joint[chain.length];
        for (int i = 0; i < chain.length; i++) {
            list[i] = chain[i].getEnd();
        }
        return list;
    }
    */

    public Joint[] getAllJoints() {
        Joint[] list = new Joint[chain.size()];
        for (int i = 0; i < chain.size(); i++) {
            list[i] = chain.get(i).getEnd();
        }
        return list;
    }
}
