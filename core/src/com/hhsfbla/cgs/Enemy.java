package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

/**
 * Base class for Enemies
 */
public class Enemy extends MovableActor {
	private int health;
	private float damagePerMS;
	private TreeMap<Integer, Animation> hurtSprite;
	private TreeMap<Integer, Animation> dyingSprite;

	@SuppressWarnings("serial")
	public Enemy() {
		//Spritesheet for different directions
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
		setOrigin(Align.bottom); //alignment for drawing

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

		// sprites for enemy dying
		final Animation leftDie = Images.getAnimation("minion-left-die-%d.png", 1, 7, 0.03f);
		final Animation rightDie = Images.getAnimation("minion-right-die-%d.png", 1, 7, 0.03f);
		final Animation downDie = Images.getAnimation("minion-down-die-%d.png", 9, 0, 0.03f);
		final Animation upDie = Images.getAnimation("minion-up-die-%d.png", 9, 0, 0.03f);
		final Animation upRightDie = Images.getAnimation("minion-up-right-%d.png", 9, 0, 0.03f);
		final Animation upLeftDie = Images.getAnimation("minion-up-left-die-%d.png", 9, 0, 0.03f);
		final Animation downLeftDie = Images.getAnimation("minion-down-left-%d.png", 9, 0, 0.03f);
		final Animation downRightDie = Images.getAnimation("minion-down-right-%d.png", 9, 0, 0.03f);
		setDyingSprite(new TreeMap<Integer, Animation>() {{
		        put(DIR_UP, upDie);
		        put(DIR_DOWN, downDie);
		        put(DIR_LEFT, leftDie);
		        put(DIR_RIGHT, rightDie);
		        put(DIR_UP_LEFT, upLeftDie);
		        put(DIR_UP_RIGHT, upRightDie);
		        put(DIR_DOWN_LEFT, downLeftDie);
		        put(DIR_DOWN_RIGHT, downRightDie);
		}});

		health = 10;
		setDamage(0.005f);
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

	public TreeMap<Integer, Animation> getDyingSprite() {
		return dyingSprite;
	}

	public void setDyingSprite(TreeMap<Integer, Animation> dyingSprite) {
		this.dyingSprite = dyingSprite;
	}

	@Override
	protected void resolveCollision(AnimatedActor actor) {
		if (actor instanceof Disc) {
			final Disc disc = (Disc) actor;
			damage(disc.getDamage());
			getLevel().remove(actor);
		}
	}

	public float getDamage() {
		return damagePerMS;
	}

	public void setDamage(float f) {
		this.damagePerMS = f;
	}

	public class HurtAction extends AnimatedAction {
		public HurtAction() {
			super(getHurtSprite());
		}
	}

	public class DyingAction extends AnimatedAction {
		public DyingAction() {
			super(getDyingSprite());
		}

		@Override
		protected void end() {
			super.end();
			getLevel().remove(Enemy.this);
		}
	}
}
