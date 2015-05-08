package game.core;

import java.util.HashMap;

public final class TextureLib {
	
	private static final HashMap<String, Texture> textures = new HashMap<>();
	
	public static final void register(String s, Texture t) {
		textures.put(s, t);
	}
	
	public static final Texture get(String s) {
		return textures.get(s);
	}
	
	public static void destroy() {
		for(Texture t : textures.values()) {
			t.destroy();
		}
	}
	
}