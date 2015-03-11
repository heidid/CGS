package com.hhsfbla.cgs;

import com.badlogic.gdx.graphics.g2d.Batch;

public class FileStack extends Obstacle {
	private static final int MAX_HEALTH = 300;

	private int health = MAX_HEALTH;
	public int enemiesTargettingMe = 0;
	public int maxEnemiesTargettingMe = 999999;
	private HealthBar healthBar = new HealthBar();

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
		if (health >= MAX_HEALTH * 3 / 4) {
			setSprite(Images.get("filestack-4.png"));
		} else if (health >= MAX_HEALTH / 2) {
			setSprite(Images.get("filestack-3.png"));
		} else if (health >= MAX_HEALTH / 4) {
			setSprite(Images.get("filestack-2.png"));
		} else if (health > 0) {
			setSprite(Images.get("filestack-1.png"));
		} else {
			setSprite(Images.get("filestack-0.png"));
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		healthBar.draw(batch, parentAlpha, getX(), getY(), health, MAX_HEALTH);
	}
}
