package game.core;

public class Bullet {
	
	private GameQuad mesh;
	private World wld;
	private float dx, dy;
	private int dmg;
	
	public Bullet(GameCore core, World wld, float x, float y, float size, float dx, float dy, int dmg) {
		mesh = new GameQuad(x, y, size, size, "player");
		this.dx = dx;
		this.dy = dy;
		this.wld = wld;
		this.dmg = dmg;
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
	}
	
	public GameQuad getMesh() {
		return this.mesh;
	}
	public int getDamage() {
		return this.dmg;
	}
}