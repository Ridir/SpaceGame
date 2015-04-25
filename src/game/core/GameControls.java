package game.core;

import org.lwjgl.input.Keyboard;

public class GameControls {
	
	private GameCore core;
	private World wld;
	private double timer;
	private Bullet bullet;
	private int speed = 4;
	private double freq = 0.25;
	
	
	public GameControls(GameCore core) {
		this.core = core;
	}
	
	
	public void input() {
		
		if(Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			wld.getPlayer().resize(1, 1);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_E)) {
			wld.getPlayer().resize(-1, -1);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_S) && Keyboard.isKeyDown(Keyboard.KEY_D)) {
			wld.getPlayer().addX((speed / 2));
			wld.getPlayer().addY((speed / 2));
		}
		
		else if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			wld.getPlayer().addY(speed);
		}
		
		else if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			wld.getPlayer().addX(speed);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_S) && Keyboard.isKeyDown(Keyboard.KEY_A)) {
			wld.getPlayer().addX(-(speed / 2));
			wld.getPlayer().addY((speed / 2));
		}
		
		else if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			wld.getPlayer().addY(speed);
		}
		
		else if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			wld.getPlayer().addX(-speed);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W) && Keyboard.isKeyDown(Keyboard.KEY_D)) {
			wld.getPlayer().addX((speed / 2));
			wld.getPlayer().addY(-(speed / 2));
		}
		
		else if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			wld.getPlayer().addY(-speed);
		}
		
		else if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			wld.getPlayer().addX(speed);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W) && Keyboard.isKeyDown(Keyboard.KEY_A)) {
			wld.getPlayer().addX(-(speed / 2));
			wld.getPlayer().addY(-(speed / 2));
		}
		
		else if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			wld.getPlayer().addX(-speed);
		}
		
		else if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			wld.getPlayer().addY(-speed);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			if(timer > freq) {
				wld.shoot(1);
				timer = 0;
			}
		}
		
		timer += wld.delta;
		
		while(Keyboard.next()) {
			if(Keyboard.getEventKeyState()) {
				//Pressed
				int k = Keyboard.getEventKey();

				switch(k) {
				case Keyboard.KEY_ESCAPE: GameCore.running = false; break;
				
				case Keyboard.KEY_G: wld.getPlayer().setColor(0, 1, 1); break;
				
				case Keyboard.KEY_H: wld.getPlayer().setColor(1, 1, 0); break;
				
				case Keyboard.KEY_J: wld.getPlayer().setColor(1, 0, 1); break;
				
				case Keyboard.KEY_K: wld.addEnemy(new Enemy(core, "Gunship", 30, 5, 1, 0, "side", true, 100, 2)); break;
				
				case Keyboard.KEY_SPACE: 
					if(timer > freq) {
						wld.shoot(1);
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
