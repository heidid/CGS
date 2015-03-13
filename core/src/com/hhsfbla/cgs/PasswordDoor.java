package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.graphics.g2d.Animation;

public class PasswordDoor extends UnblockableObstacle {
	private int type;

	public PasswordDoor(int type) {
		this(type, true);
	}

	public PasswordDoor(int type, boolean blocked) {
		setDirection(DIR_DOWN);
		setSize(1, 1/6f);
		setOriginY(5/12f);
		setType(type);
		setBlocked(blocked);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
		final String n = type == 1 ? "" : Integer.toString(type);
		setBlockedSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_DOWN, new Animation(0, Images.get("door" + n + ".png")));
		}});
		setUnblockedSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_DOWN, new Animation(0, Images.get("doormat" + n + ".png")));
		}});
		setBlockingAnimation(new TreeMap<Integer, Animation>() {{
			put(DIR_DOWN, Images.getAnimation("door" + n + "-%d.png", 10, 0, 0.03f));
		}});
		setUnblockingAnimation(new TreeMap<Integer, Animation>() {{
			put(DIR_DOWN, Images.getAnimation("door" + n + "-%d.png", 0, 10, 0.03f));
		}});
	}

	@Override
	protected void resolveCollision(AnimatedActor actor) {
		if (actor instanceof Player) {
			final Player player = (Player) actor;

			for (Item item : player.getInventory()) {
				if (item instanceof PasswordFile) {
					final PasswordFile file = (PasswordFile) item;

					if (file.getType() == type) {
						addAction(new UnblockAction());
						player.removeItem(file);
					}
				}
			}
		}
	}
}
