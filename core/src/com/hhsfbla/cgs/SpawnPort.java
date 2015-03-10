package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.graphics.g2d.Animation;

public class SpawnPort extends Port {
	public SpawnPort() {
		this(DIR_RIGHT);
		setAppearSprite(new TreeMap<Integer, Animation>() {{
			//put(DIR_UP, new Animation(0, Images.get("port-exit-down.png")));
			put(DIR_DOWN, Images.getAnimation("port-enemy-down-%d.png", 0, 8, 0.05f));
			//put(DIR_LEFT, new Animation(0, Images.get("port-exit-left.png")));
			//put(DIR_RIGHT, new Animation(0, Images.get("port-exit-right.png")));
		}});
		setDisappearSprite(new TreeMap<Integer, Animation>() {{
			//put(DIR_UP, new Animation(0, Images.get("port-exit-down.png")));
			put(DIR_DOWN, Images.getAnimation("port-enemy-down-%d.png", 8, 0, 0.05f));
			//put(DIR_LEFT, new Animation(0, Images.get("port-exit-left.png")));
			//put(DIR_RIGHT, new Animation(0, Images.get("port-exit-right.png")));
		}});
	}

	public SpawnPort(int direction) {
		super(direction);
	}

	public void spawn(MovableActor actor) {
		final float spawnX = getX() + (float) Math.cos(Math.toRadians(getDirection()));
		final float spawnY = getY() + (float) Math.sin(Math.toRadians(getDirection()));
		final Level level = getLevel();

		for (Obstacle o : level.getObstacles()) {
			if (o != this && o.isBlocked() && o.getHitbox()
					.contains(spawnX, spawnY)) {
				return;
			}
		}

		if (actor instanceof Player) {
			level.setPlayerPosition(spawnX, spawnY);
		} else if (actor instanceof Enemy) {
			level.add((Enemy) actor, spawnX, spawnY);
		} else {
			level.add(actor, spawnX, spawnY);
		}
		actor.setDirection(getDirection());
		actor.addAction(actor.new AppearAction());
	}
}
