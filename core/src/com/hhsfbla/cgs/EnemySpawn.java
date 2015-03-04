package com.hhsfbla.cgs;

import java.util.Random;
import java.util.TreeMap;

import com.badlogic.gdx.graphics.g2d.Animation;

public class EnemySpawn extends SpawnPort {
	private static final float SPAWN_DELAY = 5;
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
		setSprite(new TreeMap<Integer, Animation>() {{
	//		put(DIR_UP, new Animation(0, Images.get("port-enemy-up.png")));
			put(DIR_DOWN, new Animation(0, Images.get("port-enemy-down.png")));
			put(DIR_LEFT, new Animation(0, Images.get("port-enemy-left.png")));
			put(DIR_RIGHT, new Animation(0, Images.get("port-enemy-right.png")));
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

		time += delta;
		if (time >= delay) {
			spawn(new Enemy(new PathfindingAction(getLevel().getPlayer().getX(),
					getLevel().getPlayer().getY())));
			generateDelay();
			time = 0;
		}
	}
}
