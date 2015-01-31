package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class AnimatedActor extends Actor {
	public static int DIR_RIGHT = 0;
	public static int DIR_UP = 90;
	public static int DIR_LEFT = 180;
	public static int DIR_DOWN = 270;

	private TreeMap<Integer, Animation> orientedSprite;
	private Animation sprite;
	private float animationStateTime;
	private int direction;
	private Rectangle bounds;

	public AnimatedActor() {
		this(new TreeMap<Integer, Animation>());
	}

	public AnimatedActor(TextureRegion sprite) {
		this(new TreeMap<Integer, Animation>());
		setSprite(sprite);
	}

	public AnimatedActor(Animation sprite) {
		this(new TreeMap<Integer, Animation>());
		setSprite(sprite);
	}

	public AnimatedActor(TreeMap<Integer, Animation> orientedSprite) {
		this(orientedSprite, DIR_DOWN);
	}

	public AnimatedActor(TreeMap<Integer, Animation> orientedSprite, int direction) {
		this(orientedSprite, direction, 1, 1);
	}

	public AnimatedActor(TreeMap<Integer, Animation> orientedSprite, int direction,
			int width, int height) {
		this.orientedSprite = orientedSprite;
		bounds = new Rectangle(getX(), getY(), getWidth(), getHeight());
		setDirection(direction);
		setSize(width, height);
	}

	public TextureRegion getCurrentSpriteFrame() {
		return sprite != null ? sprite.getKeyFrame(animationStateTime) : null;
	}

	public final Animation getSprite() {
		return sprite;
	}

	public final Animation getSprite(int direction) {
		return !orientedSprite.isEmpty() ? orientedSprite.floorEntry(direction).getValue() : null;
	}

	public TreeMap<Integer, Animation> getOrientedSprite() {
		return orientedSprite;
	}

	public final void setSprite(TreeMap<Integer, Animation> orientedSprite) {
		this.orientedSprite = orientedSprite;
	}

	public final void setSprite(Animation sprite) {
		orientedSprite.clear();
		orientedSprite.put(0, sprite);
		updateSprite();
	}

	public final void setSprite(TextureRegion sprite) {
		setSprite(new Animation(0, sprite));
	}

	protected void updateSprite() {
		this.sprite = getSprite(direction);
	}

	public int getDirection() {
		return direction;
	}

	public final void setDirection(int direction) {
		this.direction = direction;
		directionChanged();
	}

	@Override
	protected void positionChanged() {
		bounds.setPosition(getX(), getY());
	}

	@Override
	protected void sizeChanged() {
		bounds.setSize(getWidth(), getHeight());
	}

	public Rectangle getBounds() {
		return bounds;
	}

	protected void directionChanged() {
		updateSprite();
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		if (sprite != null) {
			final Stage stage = getStage();
			final TextureRegion image = getCurrentSpriteFrame();

			final float gridWidth = stage.getWidth() / Level.GRID_ROWS;
			final float gridHeight = stage.getHeight() / Level.GRID_COLS;
			final float height = image.getRegionHeight() * gridWidth / image.getRegionWidth();

			batch.draw(image, getX() * gridWidth, getY() * gridHeight, getOriginX(), getOriginY(),
					gridWidth, height, getScaleX(), getScaleY(), getRotation());
			animationStateTime += Gdx.graphics.getDeltaTime();
		}
	}
}
