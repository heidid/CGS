package com.hhsfbla.cgs;

/**
 * A projectile that moves in one direction and can damage enemies
 */
public class Projectile extends MovableActor {
	private int damage;

	public Projectile(int direction) {
		setMoving(direction);
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
}
