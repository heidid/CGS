package com.hhsfbla.cgs;

public class FileStack extends Obstacle {
	private float health = 10;

	public FileStack() {
		
	}

	public void damage(float f) {
		setHealth(getHealth() - f);
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float f) {
		this.health = f;
	}

}
