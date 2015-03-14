package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.graphics.g2d.Animation;

public class SpawnPort extends Port {

	public SpawnPort() {
		this(DIR_RIGHT);
	}

	public SpawnPort(int direction) {
		super(direction);
		setAppearSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, Images.getAnimation("port-enemy-up-%d.png", 17, 0, 0.05f));
			put(DIR_DOWN, Images.getAnimation("port-enemy-down%d.png", 8, 0, 0.05f));
			put(DIR_LEFT, Images.getAnimation("port-enemy-left-%d.png", 14, 0, 0.05f));
			put(DIR_RIGHT, Images.getAnimation("port-enemy-right-%d.png", 14, 0, 0.05f));
		}});
		setDisappearSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, Images.getAnimation("port-enemy-up-%d.png", 0, 17, 0.05f));
			put(DIR_DOWN, Images.getAnimation("port-enemy-down%d.png", 0, 8, 0.05f));
			put(DIR_LEFT, Images.getAnimation("port-enemy-left-%d.png", 0, 14, 0.05f));
			put(DIR_RIGHT, Images.getAnimation("port-enemy-right-%d.png", 0, 14, 0.05f));
		}});
	}

	public void spawn(MovableActor actor) {
		final double ang = Math.toRadians(getDirection());
		final float cos = (float) Math.cos(ang);
		final float sin = (float) Math.sin(ang);
		final float blockableX = getX() + cos / 2;
		final float blockableY = getY() + sin / 2;
		final float spawnX = getX() + cos;
		final float spawnY = getY() + sin;

		final Level level = getLevel();

		for (Obstacle o : level.getObstacles()) {
			if (o != this && o.isBlocked() && o.getHitbox()
					.contains(blockableX, blockableY)) {
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
