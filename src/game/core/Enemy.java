package game.core;

public class Enemy {
	
	public static int damage, size, speed, hp, bullet;
	private String name, pattern, type;
	private float x, y, time;
	public GameQuad enemyMesh;
	private GameCore core;
	private World wld;
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
		this.enemyMesh = enemyMesh;
		
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
		enemyMesh.addY((float)(speed * wld.delta));
		enemyMesh.addX((float)(speed / 6 * wld.delta));
		if(enemyMesh.getX() + enemyMesh.getWidth() < 0 || enemyMesh.getX() + enemyMesh.getWidth() > core.width) {
			wld.removeEnemy(this);
		}
		wld.drake(enemyMesh, wld.getPlayer(), damage, core.playerhp);
		System.out.println(hp);
	}
	
	public void draw() {
		enemyMesh.draw();
		
	}
	
	public GameQuad getMesh() {
		return enemyMesh;
	}
	
	public int getHP() {
		return hp;
	}
}
