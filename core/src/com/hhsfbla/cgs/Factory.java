package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

public class Factory extends Obstacle {
	private static final float FILE_SPAWN_DELAY = 1;
	private static final float VIRUS_SPAWN_DELAY = 2;

	private boolean infected;
	private float time;

	public Factory() {
		this(DIR_RIGHT);
	}

	public Factory(int direction) {
		setSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_LEFT, Images.getAnimation("factory-left-%d.png", 0, 10, 0.05f, PlayMode.LOOP));
			put(DIR_RIGHT, Images.getAnimation("factory-right-%d.png", 0, 10, 0.05f, PlayMode.LOOP));
		}});
		setDirection(direction);
	}

	public boolean isInfected() {
		return infected;
	}

	public void setInfected(boolean infected) {
		this.infected = infected;
		setSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_LEFT, Images.getAnimation("factory-infected-left-%d.png", 1, 14, 0.05f, PlayMode.LOOP));
			put(DIR_RIGHT, Images.getAnimation("factory-infected-right-%d.png", 1, 14, 0.05f, PlayMode.LOOP));
		}});
	}

	private void spawnFile() {
		final double ang = Math.toRadians(getDirection());
		final float x = getX() + (float) Math.cos(ang);
		final float y = getY() + (float) Math.sin(ang);

		final FactoryFile file = new FactoryFile();
		getLevel().add(file, x, y);
		file.detectCollisions(0, 0);
	}

	private void spawnVirus() {
		final double ang = Math.toRadians(getDirection());
		final float x = getX() + (float) Math.cos(ang);
		final float y = getY() + (float) Math.sin(ang);

		final Virus virus = new Virus(getDirection());
		virus.addAction(virus.new AppearAction());
		getLevel().add(virus, x, y);
		virus.detectCollisions(0, 0);
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		time += delta;

		final float delay = infected ? VIRUS_SPAWN_DELAY : FILE_SPAWN_DELAY;
		if (time >= delay) {
			if (infected) {
				spawnVirus();
			} else {
				spawnFile();
			}
			time = 0;
		}
	}
}
