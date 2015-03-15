package com.hhsfbla.cgs;

public class SpawnPort extends Port {

	public SpawnPort() {
		this(DIR_RIGHT);
	}

	public SpawnPort(int direction) {
		super(direction);
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
