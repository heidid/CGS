package com.hhsfbla.cgs;

public class SpawnPort extends Port {

	public SpawnPort() {
		this(DIR_RIGHT);
	}

	public SpawnPort(int direction) {
		super(direction);
	}

	public boolean canSpawn() {
		final double ang = Math.toRadians(getDirection());
		final float blockableX = getX() + (float) Math.cos(ang) / 2;
		final float blockableY = getY() + (float) Math.sin(ang) / 2;

		for (Obstacle o : getLevel().getObstacles()) {
			if (o != this && o.isBlocked() && o.getHitbox()
					.contains(blockableX, blockableY)) {
				return false;
			}
		}

		return true;
	}

	public void spawn(MovableActor actor) {
		final double ang = Math.toRadians(getDirection());
		final float spawnX = getX() + (float) Math.cos(ang);
		final float spawnY = getY() + (float) Math.sin(ang);
		final Level level = getLevel();

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
