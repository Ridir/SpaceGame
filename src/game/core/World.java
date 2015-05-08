package game.core;

import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class World {
	
	public double blink = 3;
	private GameCore core;
	public Player player;
	private boolean hurt = false;
	private double timer, timer2;
	private double inv = 1.5;
	public long preT = System.nanoTime();
	public double delta;
	
	private ArrayList<Bullet> bullets;
	private ArrayList<Bullet> bulletTrash;
	private ArrayList<Enemy> enemies;
	private ArrayList<Enemy> enemyTrash;
	
	
	public World(GameCore core) {
		this.core = core;
	}
	public void update() {
		long curT = System.nanoTime();
		delta = ((double)(curT - preT)) / 1000000000;
		preT = curT;
		for(Bullet b : bullets) {
			b.update();
			
			if(isColiding(b.getMesh(), player.getMesh())) {
				 player.hp -= b.getDamage();
			}
			
			for(Enemy e : enemies) {
				if(isColiding(b.getMesh(), e.getMesh())) {
					e.damage(b.getDamage());
					this.removeBullet(b);

				}
			}
		}
		for(Bullet b : bulletTrash) {
			bullets.remove(b);
		}
		
		bulletTrash.clear();
		
		for(Enemy e : enemies) {
			e.update();
			
			if(isColiding(player.getMesh(), e.getMesh())) {
				player.hp -= e.getDamage();
			}
		}
		for(Enemy e : enemyTrash) {
			enemies.remove(e);
		}
		
		enemyTrash.clear();
		
		if(player.getX() + player.getWidth() > Display.getWidth()) {
			player.setX(Display.getWidth() - player.getWidth());
		}
		if(player.getX() < 0) {
			player.setX(0);
		}
		if(player.getY() + player.getHeight() > Display.getHeight()) {
			player.setY(Display.getHeight() - player.getHeight());
		}
		if(player.getY() < 0) {
			player.setY(0);
		}
		
		
//		if(hurt) {
//			timer += 1;
//			timer2 += 1;
//			if(timer == inv * core.FPS) {
//				player.setColor(0, 1, 1);
//				timer = 0;
//				timer2 = 0;
//				hurt = false;
//			}
//			if(timer2 == blink * 2) {
//				timer2 = 0;
//			}
//			else if(timer2 < blink) {
//				player.setColor(0, 0.7f, 1);
//			}
//			else if(timer2 > blink){
//				player.setColor(0.2f, 1, 1);
//			}
//		}
//		
	}
	
	public boolean isColiding(GameQuad quad1, GameQuad quad2) {
		return quad1.getY() + quad1.getHeight() > quad2.getY() && quad1.getY() < quad2.getY() + quad2.getHeight() &&
				quad1.getX() + quad1.getWidth() > quad2.getX() && quad1.getX() < quad2.getX() + quad2.getWidth();
	}
	
	public void render() {
		player.draw();
		for(Bullet b : bullets) {
			b.draw();
		}
		for(Enemy e : enemies) {
			e.draw();
		}
	}
	public void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}
	
	public void removeBullet(Bullet bullet) {
		bulletTrash.add(bullet);
	}
	
	public void addEnemy(Enemy enemy) {
		enemies.add(enemy);
	}
	
	public void removeEnemy(Enemy enemy) {
		enemyTrash.add(enemy);
	}
	
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void init() {
			
		bullets = new ArrayList<>();
		bulletTrash = new ArrayList<>();
		enemies = new ArrayList<>();
		enemyTrash = new ArrayList<>();
		player = new Player(core, core.width/2, core.height, 40, 40, 10, "player");
				
			
//			addEnemy();
	}
	

	
	public void shoot(int bullet) {
		if(bullet == 1) {
			this.addBullet(new Bullet(core, this, player.getX() + player.getWidth() / 2 - (15 / 2), player.getY() - (15 / 2), 15, 0, - 1000, 1));
		}
		else if(bullet == 2) {
			this.addBullet(new Bullet(core, this, player.getX() + player.getWidth() / 2 - (15 / 2), player.getY() - (15 / 2), 15, 0, - 1000, 2));
		}
		else if(bullet == 3) {
			this.addBullet(new Bullet(core, this, player.getX() + player.getWidth() / 2 - (15 / 2), player.getY() - (30 / 2), 30, 0, - 100, 10));
		}
	}
}
