package com.hhsfbla.cgs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * An antivirus disc that Player shoots
 */
public class Disc extends Projectile {
	public Disc(int direction) {
		super(direction);
		setSprite(new TextureRegion(new Texture(Gdx.files.internal("disc.png"))));
		setSpeed(5);
		setDamage(10);
	}

	@Override
	protected boolean detectCollisions(float dx, float dy) {
		boolean blocked = super.detectCollisions(dx, dy);
		if (blocked) getLevel().remove(this);
		// TODO: Add Disc collision animation
		return blocked;
	}
}
