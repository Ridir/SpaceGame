package game.core;

import static org.lwjgl.opengl.GL11.*;

public class GameQuad {
	
	private float x, y, width, height;
	private Texture texture;
	
	public GameQuad(float x, float y, float width, float height, String texture) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.texture = TextureLib.get(texture);
	}
	
	public void draw() {
		
		texture.bind();
		
		glBegin(GL_QUADS);
		{
			glTexCoord2f(0, 0);
			glVertex2f(x, y);
			glTexCoord2f(1, 0);
			glVertex2f(x + width, y);
			glTexCoord2f(1, 1);
			glVertex2f(x + width, y + height);
			glTexCoord2f(0, 1);
			glVertex2f(x, y + height);
		}
		glEnd();
	}
	
//	public GameQuad setColor(float r, float g, float b) {
//		this.r = r;
//		this.g = g;
//		this.b = b;
//		
//		return this;
//	}
	
	public void addX(float x) {
		this.x += x;
	}
	
	public void addY(float y) {
		this.y += y;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public void resize(float rw, float ry) {
		width += rw;
		height += ry;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}
	
	
	public static final boolean isColliding(GameQuad quad1, GameQuad quad2) {
		if(quad1.getY() + quad1.getHeight() > quad2.getY() && quad1.getY() < quad2.getY() + quad2.getHeight() && quad1.getX() + quad1.getWidth() > quad2.getX() && quad1.getX() < quad2.getX() + quad2.getWidth()) {
			return true;
		}
		return false;
	}
}