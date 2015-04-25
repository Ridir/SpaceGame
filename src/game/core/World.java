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
	public GameQuad player;
	public GameCore core;
	private GameControls controls;
	private boolean hurt = false;
	private double timer, timer2;
	private double inv = 1.5;
	public long preT = System.nanoTime();
	public double delta;
	
	private ArrayList<Bullet> bullets;
	private ArrayList<Bullet> bulletTrash;
	private ArrayList<Enemy> enemies;
	private ArrayList<Enemy> enemyTrash;
	
	public void update() {
		long curT = System.nanoTime();
		delta = ((double)(curT - preT)) / 1000000000;
		preT = curT;
		for(Bullet b : bullets) {
			b.update();
		}
		for(Bullet b : bulletTrash) {
			bullets.remove(b);
		}
		
		bulletTrash.clear();
		
		for(Enemy e : enemies) {
			e.update();
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
		
		if(hurt) {
			timer += 1;
			timer2 += 1;
			if(timer == inv * core.FPS) {
				player.setColor(0, 1, 1);
				timer = 0;
				timer2 = 0;
				hurt = false;
			}
			if(timer2 == blink * 2) {
				timer2 = 0;
			}
			else if(timer2 < blink) {
				player.setColor(0, 0.7f, 1);
			}
			else if(timer2 > blink){
				player.setColor(0.2f, 1, 1);
			}
		}
		
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
	
	
	
	public void init() {
			
		bullets = new ArrayList<>();
		bulletTrash = new ArrayList<>();
		enemies = new ArrayList<>();
		enemyTrash = new ArrayList<>();
		controls = new GameControls(core);
				
		player = new GameQuad(core.width/2, core.height, 40, 40).setColor(0, 0.7f, 1);
			
//			addEnemy();
	}
	
	public void initgl() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();	
	}
	
	public GameQuad getPlayer() {
		return player;
	}
	
	public static final void main(String[] args) {
		new GameCore();
	}
	
	public void drake(GameQuad quad1, GameQuad quad2, int damage, int hp) {
		if(quad1.getY() + quad1.getHeight() > quad2.getY() && quad1.getY() < quad2.getY() + quad2.getHeight() &&
					quad1.getX() + quad1.getWidth() > quad2.getX() && quad1.getX() < quad2.getX() + quad2.getWidth()) {
			if(timer < 1)
				hp -= hp;
				if(quad2 == this.getPlayer()) {
					hurt = true;
				}
			}
		}
	
	public void shoot(int bullet) {
		if(bullet == 1) {
			this.addBullet(new Bullet(core, this.getPlayer().getX() + this.getPlayer().getWidth() / 2 - (15 / 2), this.getPlayer().getY() - (15 / 2), 15, 0, - 1000, 1));
		}
		else if(bullet == 2) {
			this.addBullet(new Bullet(core, this.getPlayer().getX() + this.getPlayer().getWidth() / 2 - (15 / 2), this.getPlayer().getY() - (15 / 2), 15, 0, - 1000, 2));
		}
		else if(bullet == 3) {
			this.addBullet(new Bullet(core, this.getPlayer().getX() + this.getPlayer().getWidth() / 2 - (15 / 2), this.getPlayer().getY() - (30 / 2), 30, 0, - 100, 10));
		}
	}
}
