package game.core;

public class Enemy {
	private int damage, size, speed, hp, bullet;
	private String name, pattern, type;
	private float x, y, time;
	private GameQuad enemyMesh;
	private GameCore core;
	private boolean right;
	
	public Enemy(GameCore core, String name, int size, int damage, int bullet, float time, String pattern, boolean right, int speed, int hp) {
		this.damage = damage;
		this.name = name;
		this.size = size;
		this.pattern = pattern;
		this.bullet = bullet;
		this.time = time;
		this.right = right;
		this.speed = speed;
		this.core = core;
		this.hp = hp;
		
		enemyMesh = new GameQuad(x, y, size, size).setColor(1, 0.5f, 0);
		
		if(pattern == "side") {
			System.out.println(1);
			x = core.width / 8;
			if(right) {
				x = core.width - x;
			}
			y = 0;
		}	
	}
	
	public int getDamage() {
		return damage;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void update() {
		enemyMesh.addY((float)(speed * core.delta));
		enemyMesh.addX((float)(speed / 6 * core.delta));
		if(enemyMesh.getX() + enemyMesh.getWidth() < 0 || enemyMesh.getX() + enemyMesh.getWidth() > core.width) {
			core.removeEnemy(this);
		}
		core.colision(enemyMesh, core.getPlayer(), damage);
	}
	
	public void draw() {
		enemyMesh.draw();
		
	}
	
}
