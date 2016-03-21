package org.pAxDev.Logic;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3f;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;
import org.newdawn.slick.opengl.Texture;

import org.pAxDev.Objects.*;

import org.pAxDev.Objects.Entity.Type;
import org.pAxDev.Util.ImgLoader;
import org.pAxDev.Util.Options;
import org.pAxDev.Util.Screen;


public class Game {
	
	private static final Type PLAYER = null;
	static Game game;
    String TITLE = "TITLE";
	final String VERSION = "0.0.1 pre-alpha";
	
	
	Options options = new Options();
	ImgLoader imgLoader = new ImgLoader();
	Screen screen;
	Camera cam1;
	
	Joint test;
    Joint test2;
    Armature p1;
    Armature p2;
	Entity enty;
	Token tok;

	Grid map;
	
	private long lastFrame;
    private long lastFPS;
    private int fps;


    public void updateOptions(){
		
		options.readFile("options.cfg");
		
		
	}
	
	public void init(){

		screen = new Screen(options.screenWidth, options.screenHeight, options.frameCap, options.fullscreen, options.vSync, TITLE+" - "+VERSION);
		cam1 = new Camera(new Vector2f(0,0), new Vector2f(options.screenWidth, options.screenHeight));
		 enty =  new Entity(new Vector3f(400,300,1), new Vector2f(10,10), new Vector4f(1,1,1,1), PLAYER);
        //map = new Grid(130,65,90,120,10);
       map = new Grid(11,9,options.screenWidth/5,25,80);
	    test = new Joint(options.screenWidth/2, options.screenHeight/2);
        test2 = new Joint(options.screenWidth/2+10, options.screenHeight/2+10);



        p1 = new Armature(test,20,null);
        p2 = new Armature(test2,10,p1);
		Mouse.setGrabbed(true);

	}

	public void mainLoop(){
	    float angle = 0;
        Skeleton skeleton = new Skeleton(1,5);
        Joint[] joints = skeleton.getAllJoints();
        lastFPS = getTime();
       long time = getTime();
		while(!screen.isCloseRequested()){
			int delta = getDelta();
            updateFPS();

			cam1.update();
			//enty.draw();
           skeleton.update();

          //  p1.setAngle((float)Math.sin(angle)*.53f);
           // p2.setAngle(((float)Math.cos(angle)*.83f) * 1.21f);
		//	p1.update();
          //  p2.update();
          //  angle = angle + 0.05f;
            if(getTime() > time+10) {
                for (GridSquare[] gsa : map.getGridSquares()) {
                    for (GridSquare gs : gsa) {
                      //  gs.drawWire();

                     //   gs.chainCollisionCheck(skeleton.getAllJoints());

                    }
                }
                time = getTime();
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_W)){
                enty.setPositionY(enty.getPositionY()+1);
                skeleton.addArm();
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_S)){
                enty.setPositionY(enty.getPositionY()-1);
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_D)){
                enty.setPositionX(enty.getPositionX()+1);
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_A)){
                enty.setPositionX(enty.getPositionX()-1);
            }
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
				break;
			}
			screen.update();
		}
	}


	
	public void close(){
		screen.destroy();
	}
	
	public static void main(String[] args) {
		game = new Game();
		
		game.updateOptions();
		game.init();
		game.mainLoop();
		game.close();
	}
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		
		return delta;
		}

    public void updateFPS() {
        if (getTime() - lastFPS > 1000) {
            screen.setTitle(("FPS: " + fps));
            fps = 0;
            lastFPS += 1000;
        }
        fps++;
    }
		public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
		}

}
