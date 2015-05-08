package game.core;

import org.lwjgl.input.Keyboard;

public class GameControls {
	
	private GameCore core;
	private World wld;
	private Player player;
	private double timer;
	private int speed = 4;
	private double freq = 0.25;
	
	
	public GameControls(GameCore core, World wld) {
		this.core = core;
		this.wld = wld;
		this.player = wld.getPlayer();
	}
	
	
	public void input() {
		
//		if(Keyboard.isKeyDown(Keyboard.KEY_Q)) {
//			player.resize(1, 1);
//		}
//		
//		if(Keyboard.isKeyDown(Keyboard.KEY_E)) {
//			player.resize(-1, -1);
//		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_S) && Keyboard.isKeyDown(Keyboard.KEY_D)) {
			player.addX((speed / 2));
			player.addY((speed / 2));
		}
		
		else if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			player.addY(speed);
		}
		
		else if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			player.addX(speed);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_S) && Keyboard.isKeyDown(Keyboard.KEY_A)) {
			player.addX(-(speed / 2));
			player.addY((speed / 2));
		}
		
		else if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			player.addY(speed);
		}
		
		else if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			player.addX(-speed);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W) && Keyboard.isKeyDown(Keyboard.KEY_D)) {
			player.addX((speed / 2));
			player.addY(-(speed / 2));
		}
		
		else if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			player.addY(-speed);
		}
		
		else if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			player.addX(speed);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W) && Keyboard.isKeyDown(Keyboard.KEY_A)) {
			player.addX(-(speed / 2));
			player.addY(-(speed / 2));
		}
		
		else if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			player.addX(-speed);
		}
		
		else if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			player.addY(-speed);
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
				
//				case Keyboard.KEY_G: player.setColor(0, 1, 1); break;
//				
//				case Keyboard.KEY_H: player.setColor(1, 1, 0); break;
//				
//				case Keyboard.KEY_J: player.setColor(1, 0, 1); break;
				
				case Keyboard.KEY_K: wld.addEnemy(new Enemy(core, wld, "Gunship", 30, 5, 1, 0, "side", true, 100, 2)); break;
				
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
