package game.core;

import org.lwjgl.input.Keyboard;

public class GameControls {
	
	private GameCore core;
	private double timer;
	private Bullet bullet;
	
	
	public GameControls(GameCore core) {
		this.core = core;
	}
	
	
	public void input() {
		
		if(Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			core.getPlayer().resize(1, 1);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_E)) {
			core.getPlayer().resize(-1, -1);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_S) && Keyboard.isKeyDown(Keyboard.KEY_D)) {
			core.getPlayer().addX(2);
			core.getPlayer().addY(2);
		}
		
		else if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			core.getPlayer().addY(4);
		}
		
		else if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			core.getPlayer().addX(4);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_S) && Keyboard.isKeyDown(Keyboard.KEY_A)) {
			core.getPlayer().addX(-2);
			core.getPlayer().addY(2);
		}
		
		else if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			core.getPlayer().addY(4);
		}
		
		else if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			core.getPlayer().addX(-4);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W) && Keyboard.isKeyDown(Keyboard.KEY_D)) {
			core.getPlayer().addX(2);
			core.getPlayer().addY(-2);
		}
		
		else if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			core.getPlayer().addY(-4);
		}
		
		else if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			core.getPlayer().addX(4);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W) && Keyboard.isKeyDown(Keyboard.KEY_A)) {
			core.getPlayer().addX(-2);
			core.getPlayer().addY(-2);
		}
		
		else if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			core.getPlayer().addX(-4);
		}
		
		else if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			core.getPlayer().addY(-4);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			if(timer > 0.25) {
				core.shoot(1);
				timer = 0;
			}
		}
		
		timer += core.delta;
		
		while(Keyboard.next()) {
			if(Keyboard.getEventKeyState()) {
				//Pressed
				int k = Keyboard.getEventKey();

				switch(k) {
				case Keyboard.KEY_ESCAPE: GameCore.running = false; break;
				
				case Keyboard.KEY_G: core.getPlayer().setColor(0, 1, 1); break;
				
				case Keyboard.KEY_H: core.getPlayer().setColor(1, 1, 0); break;
				
				case Keyboard.KEY_J: core.getPlayer().setColor(1, 0, 1); break;
				
				case Keyboard.KEY_K: core.addEnemy(new Enemy(core, "Gunship", 30, 2, 1, 0, "side", true, 100, 5)); break;
				
				case Keyboard.KEY_SPACE: 
					if(timer > 0.25) {
						core.shoot(1);
						timer = 0;
					}
					break;
				}
			} 
			else {
				int k = Keyboard.getEventKey();
				
				switch(k) {
				}
			}
		}
	}
	
	
	
	
}
