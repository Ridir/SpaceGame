package game.core;

public class Bullet {
	
	private GameQuad mesh;
	
	private float dx, dy, size;
	private int bullet, dmg;
	private GameCore core;
	
	
	public Bullet(GameCore core, float x, float y, int size, float dx, float dy, int dmg) {
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
		 core.colision(mesh, core.getPlayer(), dmg);
	}
	public GameQuad getMesh() {
		return this.mesh;
	}
}