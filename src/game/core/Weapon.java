package game.core;

public class Weapon {
	private int damage;
	private String name;
	private String desc;
	
	public Weapon(String name, String desc, int damage) {
		this.damage = damage;
		this.name = name;
		this.desc = desc;
	}
	
	public void attack() {
		System.out.println("u did " + damage + "!");
	}
	
	public void getName() {
		System.out.println(name);
		System.out.println(desc);
	}
	
	
}
