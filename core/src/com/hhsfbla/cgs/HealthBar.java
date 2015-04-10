package com.hhsfbla.cgs;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HealthBar {
	private static final TextureRegion HEALTH_BAR = Images.get("healthbar-bar.png");
	private static final TextureRegion HEALTH_UNIT = Images.get("healthbar-health.png");

	private float width;
	private float height;
	private int yOffset;
	private int units = 120;

	public HealthBar() {
		this(2/3f, 1/12f);
	}

	public HealthBar(float width, float height) {
		this(width, height, 1);
	}

	public HealthBar(float width, float height, int yOffset) {
		this.width = width;
		this.height = height;
		this.yOffset = yOffset;
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

	public int getYOffset() {
		return yOffset;
	}

	public void setYOffset(int yOffset) {
		this.yOffset = yOffset;
	}

	public void draw(Batch batch, float parentAlpha,
			float x, float y, int health, int maxHealth) {
		final float unitWidth = width / units;
		final int unitsHealth = (int) ((double) health / maxHealth * units);
		x -= width / 2;
		y += yOffset;

		batch.draw(HEALTH_BAR, x, y, width, height);

		for (int i = 0; i < unitsHealth; i++) {
			batch.draw(HEALTH_UNIT, x + i * unitWidth, y, unitWidth, height);
		}
	}
}
