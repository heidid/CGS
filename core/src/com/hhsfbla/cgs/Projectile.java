package com.hhsfbla.cgs;

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
