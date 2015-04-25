package game.core;

public class Bullet {
	
	private GameQuad mesh;
	private World wld;
	private GameCore core;
	private float dx, dy, size;
	private int bullet, damage;
	private int dmg;
	
	public Bullet(GameCore core, float x, float y, float size, float dx, float dy, int damage) {
		mesh = new GameQuad(x, y, size, size).setColor(0, 0, 0);
		this.core = core;
		this.dx = dx;
		this.dy = dy;
	}
	

	public void draw() {
		mesh.draw();	
	}
	
	public void update() {
		mesh.addX((float)(dx * wld.delta));
		mesh.addY((float)(dy * wld.delta));
		if(mesh.getY() + mesh.getHeight() < 0) {
			wld.removeBullet(this);
		}
		wld.drake(mesh, wld.getPlayer(), damage, core.playerhp);
		
		for(Enemy e : wld.getEnemies()) {
			if(bullet == 1) {
				dmg = 1;
			}
			else if(bullet == 2) {
				dmg = 2;
			}
			else if(bullet == 3) {
				dmg = 10;
			}
			if(GameQuad.isColliding(mesh, e.getMesh())) {
				e.hp -= dmg;
			}
			if(e.getHP() <= 0) {
				wld.removeEnemy(e);
			}
		}
	}
	public GameQuad getMesh() {
		return this.mesh;
	}
}