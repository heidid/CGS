package com.hhsfbla.cgs;

public class FileStack extends Obstacle {
	private int health = 100;

	public FileStack() {
		setSprite(Images.get("filestack-4.png"));
	}

	public void damage(int damage) {
		setHealth(health - damage);
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		// TODO: Update FileStack sprite based on health
		this.health = health;
	}
}
