package game.core;

public class Bullet {
	
	private GameQuad mesh;
	
	private float dx, dy, size;
<<<<<<< HEAD
	private int bullet, damage;
=======
	private int bullet, dmg;
>>>>>>> origin/master
	private GameCore core;
	private Enemy enemy;
	
	
<<<<<<< HEAD
	public Bullet(GameCore core, float x, float y, float size, float dx, float dy, int damage) {
=======
	public Bullet(GameCore core, float x, float y, int size, float dx, float dy, int dmg) {
>>>>>>> origin/master
		mesh = new GameQuad(x, y, size, size).setColor(0, 0, 0);
		this.core = core;
		this.dx = dx;
		this.dy = dy;
		this.dmg = dmg;
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
<<<<<<< HEAD
		core.colision(mesh, core.getPlayer(), damage, core.playerhp);
//		core.colision(mesh, enemy.getMesh(), Enemy.damage, Enemy.hp);
		System.out.println(mesh);
		System.out.println(enemy.getMesh());
		System.out.println(enemy.damage);
		System.out.println(enemy.hp);
=======
		 core.colision(mesh, core.getPlayer(), dmg);
>>>>>>> origin/master
	}
	public GameQuad getMesh() {
		return this.mesh;
	}
}