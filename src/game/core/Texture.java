package game.core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL12;



public class Texture {
	
	private int id;
	private int width;
	private int height;
	private String path;
	
	public Texture(String path) {
		loadTexture(path);
	}
	
	private void loadTexture(String path) {
		this.path = path;
		
		try {
			BufferedImage img = ImageIO.read(new File(path));
			System.out.println("Successfully loaded texture\"" + path + "\"");
			
			this.width = img.getWidth();
			this.height = img.getHeight();
			int[] data = new int[this.width * this.height];
			img.getRGB(0, 0, this.getWidth(), this.getHeight(), data, 0, this.getWidth());
			System.out.println("Successfully loaded texture data");
			createTexture(data);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void createTexture(int[] pixels) {
		id = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, id);
		IntBuffer buffer = ByteBuffer.allocateDirect(pixels.length << 2).order(ByteOrder.nativeOrder()).asIntBuffer();
		buffer.put(pixels);
		buffer.flip();
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL12.GL_BGRA, GL_UNSIGNED_BYTE, buffer);
		setTexPar(GL_TEXTURE_WRAP_S, GL_CLAMP);
		setTexPar(GL_TEXTURE_WRAP_T, GL_CLAMP);
		setTexPar(GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		setTexPar(GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		System.out.println("Successfully created texture with id: " + id);
	}
	
	public Texture setTexPar(int target, int value) {
		bind();
		glTexParameteri(GL_TEXTURE_2D, target, value);
		return this;
	}
	
	public void bind() {
		glBindTexture(GL_TEXTURE_2D, id);
	}
	
	public static final void unbind() {
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	private int getHeight() {
		return this.height;
	}
	
	private int getWidth() {
		return this.width;
	}
	
	private String getPath() {
		return this.path;
	}
	
	public void destroy() {
		glDeleteTextures(id);
		System.out.println("Destroyed texture with id: " + id);
	}
}
