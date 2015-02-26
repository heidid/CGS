package com.hhsfbla.cgs;

public class Door extends UnblockableObstacle {
	private File key;

	public Door() {
		this(null);
	}

	public Door(File key) {
		this.key = key;
		setBlockedSprite(Images.get("door.png"));
		setUnblockedSprite(Images.get("doormat2.png"));
	}

	public File getKey() {
		return key;
	}

	public void setKey(File key) {
		this.key = key;
	}

	@Override
	protected void resolveCollision(AnimatedActor actor) {
		if (actor instanceof Player) {
			final Player player = (Player) actor;
			if (player.getInventory().contains(key, true)) {
				addAction(new UnblockAction());
				player.removeItem(key);
			}
		}
	}
}
