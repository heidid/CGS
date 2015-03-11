package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.graphics.g2d.Animation;

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
		setBlockingAnimation(new TreeMap<Integer, Animation>() {{
			put(DIR_DOWN, Images.getAnimation("door-%d.png", 0, 10, 0.05f));
		}});
		setUnblockingAnimation(new TreeMap<Integer, Animation>() {{
			put(DIR_DOWN, Images.getAnimation("door-%d.png", 10, 0, 0.05f));
		}});
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
