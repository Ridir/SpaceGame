package game.core;

public class Bullet {
	
	private GameQuad mesh;
	
	private float dx, dy, size;
	private int bullet, damage;
	private GameCore core;
	private Enemy enemy;
	
	
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
		mesh.addX((float)(dx * core.delta));
		mesh.addY((float)(dy * core.delta));
//		System.out.println(core.delta);
		if(mesh.getY() + mesh.getHeight() < 0) {
			core.removeBullet(this);
		}
		core.colision(mesh, core.getPlayer(), damage, core.playerhp);
//		core.colision(mesh, enemy.getMesh(), Enemy.damage, Enemy.hp);
		System.out.println(mesh);
		System.out.println(enemy.getMesh());
		System.out.println(enemy.damage);
		System.out.println(enemy.hp);
	}
	public GameQuad getMesh() {
		return this.mesh;
	}
}