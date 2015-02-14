package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
		setMoveSprite(getIdleSprite());
		setOrigin(Align.bottom); //alignment for drawing
		hurtSprite = new TreeMap<>();
		dyingSprite = new TreeMap<>();
		health = 30;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
		if (health <= 0) {
			clearActions(); //stop moving if enemy dies
			setSprite(getDyingSprite());
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
}
