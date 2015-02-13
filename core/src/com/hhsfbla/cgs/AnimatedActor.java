package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class AnimatedActor extends Actor {
	public static int DIR_RIGHT = 0;
	public static int DIR_UP = 90;
	public static int DIR_LEFT = 180;
	public static int DIR_DOWN = 270;
	public static int DIR_UP_RIGHT = 45;
	public static int DIR_UP_LEFT = 135;
	public static int DIR_DOWN_RIGHT = 315;
	public static int DIR_DOWN_LEFT = 225;

	private Level level;
	private int direction;
	private TreeMap<Integer, Vector2> orientedSize;
	private TreeMap<Integer, Animation> orientedSprite;
	private TreeMap<Integer, Hitbox> orientedHitbox;
	private Vector2 size;
	private Animation sprite;
	private Hitbox hitbox;
	private float animationStateTime;

	public AnimatedActor() {
		direction = DIR_DOWN;
		orientedSize = new TreeMap<>();
		orientedSprite = new TreeMap<>();
		orientedHitbox = new TreeMap<>();
		orientedSize.put(0, size = new Vector2());
		orientedHitbox.put(0, hitbox = new Hitbox());
		setSize(1, 1);
		setOrigin(Align.center);
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
	
	public TreeMap<Integer, Vector2> getOrientedSize() {
		return orientedSize;
	}

	@Override
	public void setSize(float width, float height) {
		size.set(width, height);
		super.setSize(width, height);
	}

	public void setSize(TreeMap<Integer, Vector2> orientedSize) {
		this.orientedSize = orientedSize;
		updateSize();
	}

	@Override
	public void setWidth(float width) {
		setSize(width, getHeight());
	}

	@Override
	public void setHeight(float height) {
		setSize(getWidth(), height);
	}

	public TextureRegion getCurrentSpriteFrame() {
		return sprite != null ? sprite.getKeyFrame(animationStateTime) : null;
	}

	public Animation getSprite() {
		return sprite;
	}

	public Animation getSprite(int direction) {
		return !orientedSprite.isEmpty()
				? orientedSprite.floorEntry(direction).getValue() : null;
	}

	public TreeMap<Integer, Animation> getOrientedSprite() {
		return orientedSprite;
	}

	public void setSprite(TreeMap<Integer, Animation> orientedSprite) {
		this.orientedSprite = orientedSprite;
		updateSprite();
	}

	public void setSprite(Animation sprite) {
		orientedSprite.clear();
		orientedSprite.put(0, sprite);
		updateSprite();
	}

	public void setSprite(TextureRegion sprite) {
		setSprite(new Animation(0, sprite));
	}

	public Hitbox getHitbox() {
		return hitbox;
	}

	public Hitbox getHitbox(int direction) {
		return !orientedHitbox.isEmpty()
				? orientedHitbox.floorEntry(direction).getValue() : null;
	}

	public TreeMap<Integer, Hitbox> getOrientedHitbox() {
		return orientedHitbox;
	}

	public void setHitbox(TreeMap<Integer, Hitbox> orientedHitbox) {
		this.orientedHitbox = orientedHitbox;
		updateHitbox();
	}

	public void setHitbox(Hitbox hitbox) {
		orientedHitbox.clear();
		orientedHitbox.put(0, hitbox);
		updateHitbox();
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		final int oldDirection = this.direction;
		this.direction = direction;
		if (direction != oldDirection) directionChanged();
	}

	protected void updateSize() {
		if (orientedSize.isEmpty()) {
			orientedSize.put(0, new Vector2(getWidth(), getHeight()));
		}
		size = orientedSize.floorEntry(direction).getValue();
		setSize(size.x, size.y);
	}

	protected void updateSprite() {
		final Animation oldSprite = this.sprite;
		this.sprite = getSprite(direction);
		if (sprite != oldSprite) animationStateTime = 0;
	}

	protected void updateHitbox() {
		hitbox = getHitbox(direction);
		if (hitbox != null) {
			hitbox.setSize(getWidth(), getHeight());
			hitbox.setCenter(getX(), getY());
		}
	}

	@Override
	protected void positionChanged() {
		updateHitbox();
	}

	@Override
	protected void sizeChanged() {
		updateHitbox();
	}

	protected void directionChanged() {
		updateSize();
		updateSprite();
		updateHitbox();
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		if (sprite != null) {
			final Stage stage = getStage();
			final TextureRegion image = getCurrentSpriteFrame();

			final float gridWidth = stage.getWidth() / Level.GRID_COLS;
			final float gridHeight = stage.getHeight() / Level.GRID_ROWS;
			final float x = (getX() - getOriginX() + 0.5f) * gridWidth;
			final float y = (getY() - getOriginY() + 0.5f) * gridHeight;
			final float width = getWidth() * gridWidth;
			final float height = image.getRegionHeight() * width
					/ image.getRegionWidth();

			batch.draw(image, x, y, getOriginX(), getOriginY(), width, height,
					getScaleX(), getScaleY(), getRotation());

			animationStateTime += Gdx.graphics.getDeltaTime();
		}
	}
}
