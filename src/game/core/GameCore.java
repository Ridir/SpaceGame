package game.core;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class GameCore {
	
	public String title = "Space Game";
	public int width = 800;
	public int height = 800;
	public static boolean running = true;
	public World wld;
	public final int FPS = 60;	
	
	private GameControls controls;
	
//	private GameQuad[][] boxes;
	
	

	public GameCore() {
		wld = new World(this);
		try {
			Display.setTitle(title);
			Display.setDisplayMode(new DisplayMode(width, height));	
			Display.create();
		} catch (Exception e) {
			System.err.println("EW ERROR EW ;(");
			e.printStackTrace();
		}
		
		initgl();
		registerTextures();
		wld.init();
		controls = new GameControls(this, wld);
		
		while(!Display.isCloseRequested() && running) {
			this.loop();
		}
		TextureLib.destroy();
		Display.destroy();
	}
	
	public void initgl() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	
	public static final void main(String[] args) {
		new GameCore();
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
	
	private void registerTextures() {
		TextureLib.register("player", new Texture("res/texture/player.png"));
	}
	
}