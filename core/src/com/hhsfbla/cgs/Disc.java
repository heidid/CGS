package com.hhsfbla.cgs;

/**
 * An antivirus disc that Player shoots
 */
public class Disc extends Projectile {
	public Disc(int direction) {
		super(direction);
		setSprite(Images.get("disc.png"));
		setSpeed(5);
		setDamage(10);
	}

	@Override
	protected boolean detectCollisions(float dx, float dy) {
		boolean blocked = super.detectCollisions(dx, dy);
		if (blocked) getLevel().remove(this);
		return blocked;
	}
}
