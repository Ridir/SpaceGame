package game.core;

public class Enemy {
	
	public int damage, size, speed, hp, bullet;
	private String name, pattern, type;
	private float x, y, time;
	public GameQuad enemyMesh;
	private GameCore core;
	private World wld;
	private boolean right;
	
	
	public Enemy(GameCore core, World wld, String name, int size, int damage, int bullet, float time, String pattern, boolean right, int speed, int hp) {
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
		this.wld = wld;
		
		enemyMesh = new GameQuad(x, y, size, size, "player");
		
		if(pattern == "side") {
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
		if(this.getHP() <= 0) {
			
			wld.removeEnemy(this);
		}
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

	public void damage(int d) {
		hp -= d;
	}
}
