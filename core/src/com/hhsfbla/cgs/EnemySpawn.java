package com.hhsfbla.cgs;

import java.util.Random;
import java.util.TreeMap;

import com.badlogic.gdx.graphics.g2d.Animation;

public class EnemySpawn extends SpawnPort {
	private static final float SPAWN_DELAY = 10;
	private static final float SPAWN_DELAY_ERROR_MARGIN = 1;

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
		setAppearAnimation(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, Images.getAnimation("port-enemy-up-%d.png", 0, 17, 0.05f));
			put(DIR_DOWN, Images.getAnimation("port-enemy-down-%d.png", 8, 0, 0.05f));
			put(DIR_LEFT, Images.getAnimation("port-enemy-left-%d.png", 14, 0, 0.05f));
			put(DIR_RIGHT, Images.getAnimation("port-enemy-right-%d.png", 14, 0, 0.05f));
		}});
		setDisappearAnimation(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, Images.getAnimation("port-enemy-up-%d.png", 17, 0, 0.05f));
			put(DIR_DOWN, Images.getAnimation("port-enemy-down-%d.png", 0, 8, 0.05f));
			put(DIR_LEFT, Images.getAnimation("port-enemy-left-%d.png", 0, 14, 0.05f));
			put(DIR_RIGHT, Images.getAnimation("port-enemy-right-%d.png", 0, 14, 0.05f));
		}});

		generateDelay();
	}

	private void generateDelay() {
		delay = SPAWN_DELAY + SPAWN_DELAY_ERROR_MARGIN
				* (2 * random.nextFloat() - 1);
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		if (isOpen()) {
			time += delta;
			if (time >= delay) {
				if (canSpawn()) spawn(new Enemy());
				generateDelay();
				time = 0;
			}
		}
	}
}
