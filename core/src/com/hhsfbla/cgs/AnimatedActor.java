package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

/**
 * Represents an actor in an Level that has an orientation and multiple sizes,
 * animated sprites, and hitboxes for each orientation
 */
public class AnimatedActor extends Actor {
	//Degree values for directions
	public static final int DIR_RIGHT = 0;
	public static final int DIR_UP = 90;
	public static final int DIR_LEFT = 180;
	public static final int DIR_DOWN = 270;
	public static final int DIR_UP_RIGHT = 45;
	public static final int DIR_UP_LEFT = 135;
	public static final int DIR_DOWN_RIGHT = 315;
	public static final int DIR_DOWN_LEFT = 225;

	private Level level;
	private int direction;
	private TreeMap<Integer, Vector2> orientedSize;
	private TreeMap<Integer, Animation> orientedSprite;
	private TreeMap<Integer, Hitbox> orientedHitbox;
	private Vector2 size;
	private Animation sprite;
	private Hitbox hitbox;
	private float animationStateTime;
	private AnimatedAction animatedAction;

	/**
	 * Creates a new AnimatedActor
	 */
	public AnimatedActor() {
		direction = DIR_RIGHT;
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

	public void setSize(TreeMap<Integer, Vector2> orientedSize) {
		this.orientedSize = orientedSize;
		updateSize();
	}

	@Override
	public void setSize(final float width, final float height) {
		setSize(new TreeMap<Integer, Vector2>(){{
			put(0, new Vector2(width, height));
		}});
	}

	@Override
	public void setWidth(float width) {
		setSize(width, getHeight());
	}

	@Override
	public void setHeight(float height) {
		setSize(getWidth(), height);
	}

	/**
	 * Gets the current frame of the current animation
	 * @return returns
	 */
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
		if (orientedSprite == null) return;
		this.orientedSprite = orientedSprite;
		updateSprite();
	}

	public void setSprite(final Animation sprite) {
		if (sprite == null) return;
		setSprite(new TreeMap<Integer, Animation>(){{ put(0, sprite); }});
	}

	public void setSprite(TextureRegion sprite) {
		if (sprite == null) return;
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
		if (orientedHitbox == null) return;
		this.orientedHitbox = orientedHitbox;
		updateHitbox();
	}

	public void setHitbox(final Hitbox hitbox) {
		if (hitbox == null) return;
		setHitbox(new TreeMap<Integer, Hitbox>(){{ put(0, hitbox); }});
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		final int oldDirection = this.direction;
		this.direction = direction;
		if (direction != oldDirection) directionChanged();
	}

	public AnimatedAction getAnimatedAction() {
		return animatedAction;
	}

	public void startAnimatedAction(AnimatedAction animatedAction) {
		animatedAction.setPreviousSprite(this.animatedAction != null
				? this.animatedAction.getPreviousSprite() : orientedSprite);
		this.animatedAction = animatedAction;
	}

	public void endAnimatedAction() {
		animatedAction = null;
	}

	public float getZValue() {
		if (this instanceof Obstacle) {
			final Obstacle obstacle = (Obstacle) this;
			if (!obstacle.isBlocked() || obstacle instanceof FirewallSwitch) {
				return Float.MAX_VALUE;
			}
		}

		return getY();
	}

	public Action addActionOnce(Action action) {
		for (Action a : getActions()) {
			final Class<?> clazz = a.getClass();
			if (clazz.isMemberClass()) {
				if (action.toString().equals(a.toString())) return a;
			} else {
				if (clazz.isInstance(action)) return a;
			}
		}

		addAction(action);
		return null;
	}

	protected void resolveCollision(AnimatedActor actor) {}

	protected void updateSize() {
		if (orientedSize.isEmpty()) {
			orientedSize.put(0, new Vector2(getWidth(), getHeight()));
		}
		size = orientedSize.floorEntry(direction).getValue();
		setOrigin(getOriginX() * size.x / getWidth(),
				getOriginY() * size.y / getHeight());
		super.setSize(size.x, size.y);
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
		updateSprite();
		updateSize();
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		if (sprite != null) {
			final Stage stage = getStage();
			final TextureRegion image = getCurrentSpriteFrame();

			final float height = image.getRegionHeight() * getWidth()
					/ image.getRegionWidth();

			// Draw with all the properties of this actor
			batch.draw(image, getX() - getOriginX(), getY() - getOriginY(),
					getOriginX(), getOriginY(), getWidth(), height,
					getScaleX(), getScaleY(), getRotation());

			animationStateTime += Gdx.graphics.getDeltaTime();
		}
	}

	public int getDirectionFacing(float x, float y) {
		final double deg = (Math.toDegrees(Math.atan2(y - getY(), x - getX()))
				+ 360) % 360;
		return (int) Math.round(deg / 45) * 45;
	}
}
