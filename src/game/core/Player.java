package game.core;

public class Player {
	
	public GameQuad mesh;
	public GameCore core;
	public int hp = 50;
	
	public Player(GameCore core, float x, float y, float width, float heigh, int hp, String texture) {
		mesh = new GameQuad(core.width/2, core.height, 40, 40, texture);
		this.hp = hp;
		this.core = core;
	}
	
	public GameQuad getMesh() {
		return this.mesh;
	}
	
	public float getX() {
		return mesh.getX();
	}
	public float getY() {
		return mesh.getY();
	}
	public float getHeight() {
		return mesh.getHeight();
	}
	public float getWidth() {
		return mesh.getWidth();
	}
	public void setX(float x) {
		mesh.setX(x);
	}
	public void setY(float y) {
		mesh.setY(y);
	}

//	public Player setColor(float r, float g, float b) {
//		mesh.setColor(r, g, b);
//		return this;
//		
//	}
	
	public void addX(float x) {
		mesh.addX(x);
	}
	
	public void addY(float y) {
		mesh.addY(y);
	}
	
	public Player draw() {
		mesh.draw();
		return this;
		
	}

}
