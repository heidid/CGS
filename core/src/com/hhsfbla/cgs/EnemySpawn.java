package com.hhsfbla.cgs;

import java.util.Random;
import java.util.TreeMap;

import com.badlogic.gdx.graphics.g2d.Animation;

public class EnemySpawn extends SpawnPort {
	private float spawnDelay = 5;
	private float spawnDelayError = 1;

	private float time;
	private float delay;
	private Random random;

	public EnemySpawn() {
		this(DIR_DOWN);
	}

	public EnemySpawn(int direction) {
		super(direction);
		random = new Random();
		setOpenSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, new Animation(0, Images.get("port-enemy-up.png")));
			put(DIR_DOWN, new Animation(0, Images.get("port-enemy-down.png")));
			put(DIR_LEFT, new Animation(0, Images.get("port-enemy-left.png")));
			put(DIR_RIGHT, new Animation(0, Images.get("port-enemy-right.png")));
		}});
		generateDelay();
	}

	public float getSpawnDelay() {
		return spawnDelay;
	}

	public void setSpawnDelay(float spawnDelay) {
		this.spawnDelay = spawnDelay;
		generateDelay();
	}

	public float getSpawnDelayError() {
		return spawnDelayError;
	}

	public void setSpawnDelayError(float spawnDelayError) {
		this.spawnDelayError = spawnDelayError;
		generateDelay();
	}

	private void generateDelay() {
		delay = spawnDelay + spawnDelayError
				* (2 * random.nextFloat() - 1);
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		if (isOpen()) {
			time += delta;
			if (time >= delay) {
				if (canSpawn()) spawn((random.nextDouble() < 0.1) ? new Virus() : new Enemy());
				generateDelay();
				time = 0;
			}
		}
	}
}
