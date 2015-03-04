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

		for (Obstacle o : getLevel().getObstacles()) {
			if (o != this && o.getX() == spawnX && o.getY() == spawnY
					&& o.isBlocked()) {
				return;
			}
		}

		if (!getLevel().getActors().contains(actor, true)) getLevel().add(actor);
		actor.setPosition(spawnX, spawnY);
		actor.addAction(actor.new AppearAction());
	}
}
