package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class MovableActor extends AnimatedActor {
	private float speed;
	private TreeMap<Integer, Animation> idleSprite;
	private TreeMap<Integer, Animation> moveSprite;
	private boolean moving;
	private Array<AnimatedActor> collisions;

	public MovableActor() {
		idleSprite = new TreeMap<>();
		moveSprite = new TreeMap<>();
		collisions = new Array<>();
		setSpeed(1.0f);
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public TreeMap<Integer, Animation> getIdleSprite() {
		return idleSprite;
	}

	public TreeMap<Integer, Animation> getMoveSprite() {
		return moveSprite;
	}

	public void setIdleSprite(TreeMap<Integer, Animation> idleSprite) {
		this.idleSprite = idleSprite;
		setSprite(idleSprite);
	}

	public void setIdleSprite(Animation sprite) {
		idleSprite.clear();
		idleSprite.put(0, sprite);
		setSprite(sprite);
	}

	public void setIdleSprite(TextureRegion sprite) {
		setIdleSprite(new Animation(0, sprite));
	}

	public void setMoveSprite(TreeMap<Integer, Animation> moveSprite) {
		this.moveSprite = moveSprite;
	}

	public void setMoveSprite(Animation sprite) {
		moveSprite.clear();
		moveSprite.put(0, sprite);
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoveSprite(TextureRegion sprite) {
		setMoveSprite(new Animation(0, sprite));
	}

	public void setMoving(int direction) {
		moving = true;
		setDirection(direction);
		setSprite(getMoveSprite());
	}

	public void setIdle() {
		moving = false;
		setSprite(getIdleSprite());
	}

	protected boolean detectCollisions(float dx, float dy) {
		if (getHitbox() == null) return false;
		final Hitbox newBounds = new Hitbox(getHitbox()).translate(dx, dy);
		collisions.clear();

		for (AnimatedActor actor : getLevel().getActors()) {
			if (actor != this && actor.getHitbox() != null
					&& actor.getHitbox().overlaps(newBounds)) {
				if (actor instanceof Obstacle && ((Obstacle) actor).isBlocked()) {
					return true;
				}

				collisions.add(actor);
			}
		}

		for (AnimatedActor actor : collisions) actor.resolveCollision(this);
		return false;
	}

	@Override
	public void act(float delta) {
		if (moving) {
			final double r = Math.toRadians(getDirection());
			final float d = speed * Gdx.graphics.getDeltaTime();
			final float dx = d * (float) Math.cos(r);
			final float dy = d * (float) Math.sin(r);
			if (!detectCollisions(dx, dy)) setPosition(getX() + dx, getY() + dy);
		}
		super.act(delta);
	}
}
