package com.hhsfbla.cgs;

public class FileStack extends Obstacle {
	private static final int FULL_HEALTH = 300;

	private int health = FULL_HEALTH;
	public int enemiesTargettingMe = 0;
	public int maxEnemiesTargettingMe = 2;
	
	public FileStack() {
		setOriginY(1/3f);
		updateOrientedSprite();
	}

	public void damage(int damage) {
		setHealth(health - damage);
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
		updateOrientedSprite();
	}

	protected void updateOrientedSprite() {
		if (health >= FULL_HEALTH * 3 / 4) {
			setSprite(Images.get("filestack-4.png"));
		} else if (health >= FULL_HEALTH / 2) {
			setSprite(Images.get("filestack-3.png"));
		} else if (health >= FULL_HEALTH / 4) {
			setSprite(Images.get("filestack-2.png"));
		} else if (health > 0) {
			setSprite(Images.get("filestack-1.png"));
		} else {
			setSprite(Images.get("filestack-0.png"));
		}
	}
}
