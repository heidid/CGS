package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;

public class TemporaryEnemySpawn extends EnemySpawn {
	public static final int MAX_HEALTH = 30;

	private int health = MAX_HEALTH;
	private HealthBar healthBar = new HealthBar();

	public TemporaryEnemySpawn() {
		this(DIR_DOWN);
	}

	public TemporaryEnemySpawn(int direction) {
		super(direction);
		setOpenSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, new Animation(0, Images.get("port-trojan-up.png")));
			put(DIR_DOWN, new Animation(0, Images.get("port-trojan-down.png")));
			put(DIR_LEFT, new Animation(0, Images.get("port-trojan-left.png")));
			put(DIR_RIGHT, new Animation(0, Images.get("port-trojan-right.png")));
		}});
		setAppearAnimation(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, Images.getAnimation("port-trojan-up-%d.png", 17, 0, 0.05f));
			put(DIR_DOWN, Images.getAnimation("port-trojan-down%d.png", 8, 0, 0.05f));
			put(DIR_LEFT, Images.getAnimation("port-trojan-left-%d.png", 14, 0, 0.05f));
			put(DIR_RIGHT, Images.getAnimation("port-trojan-right-%d.png", 14, 0, 0.05f));
		}});
		setDisappearAnimation(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, Images.getAnimation("port-trojan-up-%d.png", 0, 17, 0.05f));
			put(DIR_DOWN, Images.getAnimation("port-trojan-down%d.png", 0, 8, 0.05f));
			put(DIR_LEFT, Images.getAnimation("port-trojan-left-%d.png", 0, 14, 0.05f));
			put(DIR_RIGHT, Images.getAnimation("port-trojan-right-%d.png", 0, 14, 0.05f));
		}});

		setSpawnDelay(2);
		setSpawnDelayError(1);

		setOpen(false);
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
		if (health <= 0) addAction(new DisappearAction());
	}

	public void damage(int damage) {
		setHealth(health - damage);
	}

	@Override
	protected void resolveCollision(AnimatedActor actor) {
		if (isOpen() && actor instanceof Disc) {
			damage(((Disc) actor).getDamage());
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if (isOpen()) {
			healthBar.draw(batch, parentAlpha, getX(), getY() - 1/4f, health, MAX_HEALTH);
		}
	}
}
