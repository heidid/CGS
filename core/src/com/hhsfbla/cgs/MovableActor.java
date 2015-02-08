package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MovableActor extends AnimatedActor {
	private float speed;
	private TreeMap<Integer, Animation> idleSprite;
	private TreeMap<Integer, Animation> moveSprite;
	private boolean moving;

	public MovableActor() {
		this(new TreeMap<Integer, Animation>());
		this.idleSprite = new TreeMap<>();
		this.moveSprite = new TreeMap<>();
	}

	public MovableActor(Animation sprite) {
		super(sprite);
		this.idleSprite = new TreeMap<>();
		this.moveSprite = new TreeMap<>();
		setIdleSprite(sprite);
	}

	public MovableActor(TextureRegion sprite) {
		super(sprite);
		this.idleSprite = new TreeMap<>();
		this.moveSprite = new TreeMap<>();
		setIdleSprite(sprite);
	}

	public MovableActor(TreeMap<Integer, Animation> idleSprite) {
		super(idleSprite);
		speed = 1;
		this.idleSprite = idleSprite;
		this.moveSprite = new TreeMap<>();
	}

	public MovableActor(TreeMap<Integer, Animation> idleSprite,
			TreeMap<Integer, Animation> moveSprite, int direction) {
		super(idleSprite, direction);
		speed = 1;
		this.idleSprite = idleSprite;
		this.moveSprite = moveSprite;
	}

	public MovableActor(TreeMap<Integer, Animation> idleSprite,
			TreeMap<Integer, Animation> moveSprite, int direction,
			float speed) {
		super(idleSprite, direction);
		this.speed = speed;
		this.idleSprite = idleSprite;
		this.moveSprite = moveSprite;
	}

	public MovableActor(TreeMap<Integer, Animation> idleSprite,
			TreeMap<Integer, Animation> moveSprite, int direction,
			float speed, float width, float height) {
		super(idleSprite, direction, width, height);
		this.speed = speed;
		this.idleSprite = idleSprite;
		this.moveSprite = moveSprite;
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
		updateSprite();
	}

	public void setIdle() {
		moving = false;
		setSprite(getIdleSprite());
	}

	private boolean checkCollisions(float dx, float dy) {
		final float x = getX() + getOriginX() + dx;
		final float y = getY() + getOriginY() + dy;

		for (Obstacle o : getLevel().getObstacles()) {
			if (o.getBounds().contains(x, y)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void act(float delta) {
		if (moving) {
			final double r = Math.toRadians(getDirection());
			final float d = speed * Gdx.graphics.getDeltaTime();
			final float dx = d * (float) Math.cos(r);
			final float dy = d * (float) Math.sin(r);
			if (!checkCollisions(dx, dy)) setPosition(getX() + dx, getY() + dy);
		}
		super.act(delta);
	}
}
