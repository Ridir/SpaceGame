package game.core;

public class Bullet {
	
	private GameQuad mesh;
	
	private float dx, dy, size;
	private int bullet;
	private GameCore core;
	
	
	public Bullet(GameCore core, float x, float y, float size, float dx, float dy) {
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
		if(mesh.getY() + mesh.getHeight() > core.getPlayer().getY() + core.getPlayer().getHeight() && mesh.getX() + mesh.getHeight() > core.getPlayer().getY() + core.getPlayer().getHeight()){
			core.getPlayer().setColor(1, 0, 0);
		}
		else {
			core.getPlayer().setColor(0, 1, 1);
		}
	}
	public GameQuad getMesh() {
		return this.mesh;
	}
}