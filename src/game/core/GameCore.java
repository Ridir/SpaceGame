package game.core;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class GameCore {
	
	public String title = "Space Game";
	public int width = 800;
	public int height = 600;
	public static boolean running = true;
	public World wld;
	public int playerhp = 50;
	public static final int FPS = 60;	
	
	private GameControls controls;
	
//	private GameQuad[][] boxes;
	
	

	public GameCore() {
		try {
			Display.setTitle(title);
			Display.setDisplayMode(new DisplayMode(width, height));	
			Display.create();
		} catch (Exception e) {
			System.err.println("EW ERROR EW ;(");
			e.printStackTrace();
		}
		
		wld.init();
		wld.initgl();
		
		while(!Display.isCloseRequested() && running) {
			loop();
		}
		Display.destroy();
	}
	
	
	private void loop() {
		glClearColor(1, 1, 1, 1);
		glClear(GL_COLOR_BUFFER_BIT);
		//Render
		controls.input();
		wld.update();
		wld.render();
		Display.update();
		Display.sync(FPS);
	}
	
	
	
}