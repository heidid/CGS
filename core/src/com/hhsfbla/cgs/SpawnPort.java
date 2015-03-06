package com.hhsfbla.cgs;

public class SpawnPort extends Port {
	public SpawnPort() {
		this(DIR_RIGHT);
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
		actor.addAction(actor.new AppearAction());
	}
}
