package com.hhsfbla.cgs;

public class Door extends Obstacle {
	private File key;

	public Door() {
		this(null);
	}

	public Door(File key) {
		this.key = key;
		setSprite(Images.get("door.png"));
	}

	public File getKey() {
		return key;
	}

	public void setKey(File key) {
		this.key = key;
	}

	@Override
	public void setBlocked(boolean blocked) {
		super.setBlocked(blocked);
		if (!blocked) setSprite(Images.get("conveyor-belt-right.png"));
	}

	@Override
	protected void resolveCollision(AnimatedActor actor) {
		if (actor instanceof Player) {
			final Player player = (Player) actor;
			if (player.getInventory().contains(key, true)) {
				// TODO: Add door animation
				setBlocked(false);
				player.removeItem(key);
			}
		}
	}
}
