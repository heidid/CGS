package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

/**
 * Base class for Enemies
 */
public class Enemy extends MovableActor {
	private int health;
	private int damage;
	private TreeMap<Integer, Animation> hurtSprite;

	public Enemy() {
		this(null);
	}

	public Enemy(int direction) {
		this(direction, null);
	}

	public Enemy(Action action) {
		this(DIR_RIGHT, action);
	}

	@SuppressWarnings("serial")
	public Enemy(int direction, Action action) {
		super(direction);

		// Spritesheet for different directions
		setIdleSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, new Animation(0, Images.get("minion-up.png")));
			put(DIR_DOWN, new Animation(0, Images.get("minion-down.png")));
			put(DIR_LEFT, new Animation(0, Images.get("minion-left.png")));
			put(DIR_RIGHT, new Animation(0, Images.get("minion-right.png")));
			put(DIR_UP_LEFT, new Animation(0, Images.get("minion-up-left.png")));
			put(DIR_UP_RIGHT, new Animation(0, Images.get("minion-up-right.png")));
			put(DIR_DOWN_LEFT, new Animation(0, Images.get("minion-down-left.png")));
			put(DIR_DOWN_RIGHT, new Animation(0, Images.get("minion-down-right.png")));
		}});

		// moving animations
		final Animation upMove = new Animation(0.1f,
				Images.get("minion-up.png"),
				Images.get("minion-up-move.png"));
		upMove.setPlayMode(PlayMode.LOOP);
		setMoveSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, upMove);
			put(DIR_DOWN, new Animation(0.05f,
					Images.get("minion-down.png"),
					Images.get("minion-down.png")));
			put(DIR_LEFT, new Animation(0, Images.get("minion-left.png")));
			put(DIR_RIGHT, new Animation(0, Images.get("minion-right.png")));
			put(DIR_UP_LEFT, new Animation(0, Images.get("minion-up-left.png")));
			put(DIR_UP_RIGHT, new Animation(0, Images.get("minion-up-right.png")));
			put(DIR_DOWN_LEFT, new Animation(0, Images.get("minion-down-left.png")));
			put(DIR_DOWN_RIGHT, new Animation(0, Images.get("minion-down-right.png")));
		}});

		// sprites for enemy being hit
		setHurtSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, new Animation(0.05f,
					Images.get("minion-up copy.png"),
					Images.get("minion-up.png")));
			put(DIR_DOWN, new Animation(0.05f,
					Images.get("minion-down copy.png"),
					Images.get("minion-down.png")));
			put(DIR_LEFT, new Animation(0.05f,
					Images.get("minion-left copy.png"),
					Images.get("minion-left.png")));
			put(DIR_RIGHT, new Animation(0.05f,
					Images.get("minion-right copy.png"),
					Images.get("minion-right.png")));
			put(DIR_UP_LEFT, new Animation(0.05f,
					Images.get("minion-up-left copy.png"),
					Images.get("minion-up-left.png")));
			put(DIR_UP_RIGHT, new Animation(0.05f,
					Images.get("minion-up-right copy.png"),
					Images.get("minion-up-right.png")));
			put(DIR_DOWN_LEFT, new Animation(0.05f,
					Images.get("minion-down-left copy.png"),
					Images.get("minion-down-left.png")));
			put(DIR_DOWN_RIGHT, new Animation(0.05f,
					Images.get("minion-down-right copy.png"),
					Images.get("minion-down-right.png")));
		}});

		// sprites for enemy spawning and dying
		setAppearSprite(new TreeMap<Integer, Animation>() {{
		        put(DIR_UP, Images.getAnimation("minion-up-die-%d.png", 0, 9, 0.03f));
		        put(DIR_DOWN, Images.getAnimation("minion-down-die-%d.png", 0, 9, 0.03f));
		        put(DIR_LEFT, Images.getAnimation("minion-left-die-%d.png", 7, 1, 0.03f));
		        put(DIR_RIGHT, Images.getAnimation("minion-right-die-%d.png", 7, 1, 0.03f));
		        put(DIR_UP_LEFT, Images.getAnimation("minion-up-left-die-%d.png", 0, 9, 0.03f));
		        put(DIR_UP_RIGHT, Images.getAnimation("minion-up-right-%d.png", 0, 9, 0.03f));
		        put(DIR_DOWN_LEFT, Images.getAnimation("minion-down-left-%d.png", 0, 9, 0.03f));
		        put(DIR_DOWN_RIGHT, Images.getAnimation("minion-down-right-%d.png", 0, 9, 0.03f));
		}});
		setDisappearSprite(new TreeMap<Integer, Animation>() {{
		        put(DIR_UP, Images.getAnimation("minion-up-die-%d.png", 9, 0, 0.03f));
		        put(DIR_DOWN, Images.getAnimation("minion-down-die-%d.png", 9, 0, 0.03f));
		        put(DIR_LEFT, Images.getAnimation("minion-left-die-%d.png", 1, 7, 0.03f));
		        put(DIR_RIGHT, Images.getAnimation("minion-right-die-%d.png", 1, 7, 0.03f));
		        put(DIR_UP_LEFT, Images.getAnimation("minion-up-left-die-%d.png", 9, 0, 0.03f));
		        put(DIR_UP_RIGHT, Images.getAnimation("minion-up-right-%d.png", 9, 0, 0.03f));
		        put(DIR_DOWN_LEFT, Images.getAnimation("minion-down-left-%d.png", 9, 0, 0.03f));
		        put(DIR_DOWN_RIGHT, Images.getAnimation("minion-down-right-%d.png", 9, 0, 0.03f));
		}});

		setOrigin(Align.bottom); // alignment for drawing

		health = 10;
		setDamage(5);
		if (action != null) addAction(action);
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
		if (health <= 0) {
			clearActions();
			addActionOnce(new DyingAction());
		}
	}

	public void damage(int damage) {
		HurtAction hurtAction = (HurtAction) addActionOnce(new HurtAction());
		if (hurtAction != null) hurtAction.restart();
		setHealth(health - damage);
	}

	public TreeMap<Integer, Animation> getHurtSprite() {
		return hurtSprite;
	}

	public void setHurtSprite(TreeMap<Integer, Animation> hurtSprite) {
		this.hurtSprite = hurtSprite;
	}

	@Override
	protected void resolveCollision(AnimatedActor actor) {
		if (actor instanceof Disc) {
			final Disc disc = (Disc) actor;
			damage(disc.getDamage());
			getLevel().remove(actor);
		}
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public class HurtAction extends AnimatedAction {
		public HurtAction() {
			super(getHurtSprite());
		}
	}

	public class DyingAction extends DisappearAction {
		@Override
		protected void end() {
			super.end();
			getLevel().remove(Enemy.this);
		}
	}
}
