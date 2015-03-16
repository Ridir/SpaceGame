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
	
	public static final int FPS = 60;
	public String title = "Space Game";
	public int width = 800;
	public int height = 600;
	public static boolean running = true;
	public long preT = System.nanoTime();
	public double delta;
	public double blink = 3;
	private boolean hurt = false;
	private int timer, timer2;
	private double inv = 1.5;
	public int playerhp;
	
	private ArrayList<Bullet> bullets;
	private ArrayList<Bullet> bulletTrash;
	private ArrayList<Enemy> enemies;
	private ArrayList<Enemy> enemyTrash;
	public GameQuad player;
	private GameControls controls;
	
	
//	private GameQuad[][] boxes;
	

	public GameCore() {
		init();
		initgl();
		
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
		update();
		render();
		
		Display.update();
		Display.sync(FPS);
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
	
	
	private void update() {
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
			if(timer == inv * FPS) {
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
	
	private void render() {
		player.draw();
		for(Bullet b : bullets) {
			b.draw();
		}
		for(Enemy e : enemies) {
			e.draw();
		}
	}
	
	private void init() {
		try {
			
			bullets = new ArrayList<>();
			bulletTrash = new ArrayList<>();
			enemies = new ArrayList<>();
			enemyTrash = new ArrayList<>();
			controls = new GameControls(this);
			
			player = new GameQuad(width/2, height, 40, 40).setColor(0, 0.7f, 1);
			
			Display.setTitle(title);
			Display.setDisplayMode(new DisplayMode(width, height));
			
			Display.create();
			
		} catch (Exception e) {
			System.err.println("EW ERROR EW ;(");
			e.printStackTrace();
		}
	}
	
	private void initgl() {
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
	
	public void colision(GameQuad quad1, GameQuad quad2, int damage, int hp) {
		if(quad1.getY() + quad1.getHeight() > quad2.getY() && quad1.getY() < quad2.getY() + quad2.getHeight() && quad1.getX() + quad1.getWidth() > quad2.getX() && quad1.getX() < quad2.getX() + quad2.getWidth()){
			if(timer < 1)
				hp -= hp;
				if(quad2 == this.getPlayer())
					hurt = true;
		}
	}
	
	public void shoot(int bullet) {
		if(bullet == 1) 
			this.addBullet(new Bullet(this, this.getPlayer().getX() + this.getPlayer().getWidth() / 2 - (15 / 2), this.getPlayer().getY() - 15, 15, 0, - 1000, 1));
		else if(bullet == 3)
			this.addBullet(new Bullet(this, this.getPlayer().getX() + this.getPlayer().getWidth() / 2 - (15 / 2), this.getPlayer().getY() - 15, 30, 0, - 200, 3));
		
		else if(bullet == 2)
			this.addBullet(new Bullet(this, this.getPlayer().getX() + this.getPlayer().getWidth() / 2 - (15 / 2), this.getPlayer().getY() - 15, 15, 0, 1000, 1));
	}
}