package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

/**
 * Base class for Enemies
 * @author Simon
 *
 */
public class Enemy extends MovableActor {
	private int health;
	private TreeMap<Integer, Animation> hurtSprite;
	private TreeMap<Integer, Animation> dyingSprite;

	@SuppressWarnings("serial")
	public Enemy() {
		//Spritesheet for different directions
		setIdleSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("minion-up.png")))));
			put(DIR_DOWN, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("minion-down.png")))));
			put(DIR_LEFT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("minion-left.png")))));
			put(DIR_RIGHT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("minion-right.png")))));
			put(DIR_UP_LEFT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("minion-up-left.png")))));
			put(DIR_UP_RIGHT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("minion-up-right.png")))));
			put(DIR_DOWN_LEFT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("minion-down-left.png")))));
			put(DIR_DOWN_RIGHT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("minion-down-right.png")))));
		}});
		final Animation upMove = new Animation(0.1f, 
				new TextureRegion(new Texture(Gdx.files.internal("minion-up.png"))), 
				new TextureRegion(new Texture(Gdx.files.internal("minion-up-move.png"))));
		upMove.setPlayMode(PlayMode.LOOP);
		setMoveSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, upMove);
			put(DIR_DOWN, new Animation(0.05f, 
					new TextureRegion(new Texture(Gdx.files.internal("minion-down.png"))), 
					new TextureRegion(new Texture(Gdx.files.internal("minion-down.png")))));
			put(DIR_LEFT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("minion-left.png")))));
			put(DIR_RIGHT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("minion-right.png")))));
			put(DIR_UP_LEFT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("minion-up-left.png")))));
			put(DIR_UP_RIGHT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("minion-up-right.png")))));
			put(DIR_DOWN_LEFT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("minion-down-left.png")))));
			put(DIR_DOWN_RIGHT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("minion-down-right.png")))));
		}});
		setOrigin(Align.bottom); //alignment for drawing

		// sprites for enemy being hit
		setHurtSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, new Animation(0.05f, 
					new TextureRegion(new Texture(Gdx.files.internal("minion-up copy.png"))),
					new TextureRegion(new Texture(Gdx.files.internal("minion-up.png")))));
			put(DIR_DOWN, new Animation(0.05f, 
					new TextureRegion(new Texture(Gdx.files.internal("minion-down copy.png"))),
					new TextureRegion(new Texture(Gdx.files.internal("minion-down.png")))));
			put(DIR_LEFT, new Animation(0.05f, 
					new TextureRegion(new Texture(Gdx.files.internal("minion-left copy.png"))),
					new TextureRegion(new Texture(Gdx.files.internal("minion-left.png")))));
			put(DIR_RIGHT, new Animation(0.05f, 
					new TextureRegion(new Texture(Gdx.files.internal("minion-right copy.png"))),
					new TextureRegion(new Texture(Gdx.files.internal("minion-right.png")))));
			put(DIR_UP_LEFT, new Animation(0.05f, 
					new TextureRegion(new Texture(Gdx.files.internal("minion-up-left copy.png"))),
					new TextureRegion(new Texture(Gdx.files.internal("minion-up-left.png")))));
			put(DIR_UP_RIGHT, new Animation(0.05f, 
					new TextureRegion(new Texture(Gdx.files.internal("minion-up-right copy.png"))),
					new TextureRegion(new Texture(Gdx.files.internal("minion-up-right.png")))));
			put(DIR_DOWN_LEFT, new Animation(0.05f, 
					new TextureRegion(new Texture(Gdx.files.internal("minion-down-left copy.png"))),
					new TextureRegion(new Texture(Gdx.files.internal("minion-down-left.png")))));
			put(DIR_DOWN_RIGHT, new Animation(0.05f, 
					new TextureRegion(new Texture(Gdx.files.internal("minion-down-right copy.png"))),
					new TextureRegion(new Texture(Gdx.files.internal("minion-down-right.png")))));
		}});

		// sprites for enemy dying
		final Animation leftDie = new Animation(0.03f,
            new TextureRegion(new Texture(Gdx.files.internal("minion-left-die-1.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("minion-left-die-2.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("minion-left-die-3.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("minion-left-die-4.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("minion-left-die-5.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("minion-left-die-6.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("minion-left-die-7.png"))));
		final Animation rightDie = new Animation(0.03f,
            new TextureRegion(new Texture(Gdx.files.internal("minion-right-die-1.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("minion-right-die-2.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("minion-right-die-3.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("minion-right-die-4.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("minion-right-die-5.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("minion-right-die-6.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("minion-right-die-7.png"))));
		final Animation downDie = new Animation(0.03f,
			new TextureRegion(new Texture(Gdx.files.internal("minion-down-die-9.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-down-die-8.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-down-die-7.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-down-die-6.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-down-die-5.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-down-die-4.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-down-die-3.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-down-die-2.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-down-die-1.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-down-die-0.png"))));
		final Animation upDie = new Animation(0.03f,
			new TextureRegion(new Texture(Gdx.files.internal("minion-up-die-9.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-up-die-8.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-up-die-7.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-up-die-6.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-up-die-5.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-up-die-4.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-up-die-3.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-up-die-2.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-up-die-1.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-up-die-0.png"))));
		final Animation upRightDie = new Animation(0.03f,
			new TextureRegion(new Texture(Gdx.files.internal("minion-up-right-9.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-up-right-8.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-up-right-7.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-up-right-6.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-up-right-5.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-up-right-4.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-up-right-3.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-up-right-2.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-up-right-1.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-up-right-0.png"))));
		final Animation upLeftDie = new Animation(0.03f,
			new TextureRegion(new Texture(Gdx.files.internal("minion-up-left-die-9.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-up-left-die-8.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-up-left-die-7.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-up-left-die-6.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-up-left-die-5.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-up-left-die-4.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-up-left-die-3.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-up-left-die-2.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-up-left-die-1.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-up-left-die-0.png"))));
		final Animation downLeftDie = new Animation(0.03f,
			new TextureRegion(new Texture(Gdx.files.internal("minion-down-left-9.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-down-left-8.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-down-left-7.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-down-left-6.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-down-left-5.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-down-left-4.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-down-left-3.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-down-left-2.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-down-left-1.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-down-left-0.png"))));
		final Animation downRightDie = new Animation(0.03f,
			new TextureRegion(new Texture(Gdx.files.internal("minion-down-right-9.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-down-right-8.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-down-right-7.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-down-right-6.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-down-right-5.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-down-right-4.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-down-right-3.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-down-right-2.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-down-right-1.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("minion-down-right-0.png"))));
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

		health = 30;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
		if (health <= 0) {
			clearActions(); //stop moving if enemy dies
			addAction(new DyingAction());
		}
	}

	public void damage(int damage) {
		setSprite(getHurtSprite());
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

	public class HurtAction extends TemporalAction {
		@Override
		protected void begin() {
			setSprite(getHurtSprite());
			setDuration(getSprite().getAnimationDuration());
		}

		@Override
		protected void update(float percent) {}

		@Override
		protected void end() {
			getLevel().remove(Enemy.this);
		}
	}

	public class DyingAction extends TemporalAction {
		@Override
		protected void begin() {
			setSprite(getDyingSprite());
			setDuration(getSprite().getAnimationDuration());
		}

		@Override
		protected void update(float percent) {}

		@Override
		protected void end() {
			getLevel().remove(Enemy.this);
		}
	}
}
