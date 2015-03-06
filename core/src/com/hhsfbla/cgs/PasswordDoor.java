package com.hhsfbla.cgs;

public class PasswordDoor extends UnblockableObstacle {
	private File key;

	public PasswordDoor() {
		this(null);
	}

	public PasswordDoor(File key) {
		this.key = key;
		setBlockedSprite(Images.get("door.png"));
		setUnblockedSprite(Images.get("doormat.png"));
		setOriginY(5/12f);
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
