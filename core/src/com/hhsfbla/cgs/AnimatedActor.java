package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
	private StageScreen screen;
	private int direction;
	private TreeMap<Integer, Vector2> orientedSize;
	private TreeMap<Integer, Animation> orientedSprite;
	private TreeMap<Integer, Hitbox> orientedHitbox;
	private Vector2 size;
	private Animation sprite;
	private Hitbox hitbox;
	private float animationStateTime;

	public AnimatedActor() {
		this(new TreeMap<Integer, Animation>());
	}

	public AnimatedActor(TextureRegion sprite) {
		this();
		setSprite(sprite);
	}

	public AnimatedActor(Animation sprite) {
		this();
		setSprite(sprite);
	}

	public AnimatedActor(TreeMap<Integer, Animation> orientedSprite) {
		this(orientedSprite, DIR_DOWN);
	}

	public AnimatedActor(TreeMap<Integer, Animation> orientedSprite,
			int direction) {
		this(orientedSprite, direction, 1, 1);
	}

	public AnimatedActor(TreeMap<Integer, Animation> orientedSprite,
			int direction, float width, float height) {
		this(orientedSprite, direction, width, height, new Hitbox());
	}

	public AnimatedActor(TreeMap<Integer, Animation> orientedSprite,
			int direction, float width, float height, final Hitbox hitbox) {
		this(orientedSprite, direction, width, height,
				new TreeMap<Integer, Hitbox>() {{ put(0, hitbox); }});
	}

	public AnimatedActor(TreeMap<Integer, Animation> orientedSprite,
			int direction, TreeMap<Integer, Vector2> orientedSize) {
		this(orientedSprite, direction, orientedSize,
				new TreeMap<Integer, Hitbox>() {{ put(0, new Hitbox()); }});
	}

	public AnimatedActor(TreeMap<Integer, Animation> orientedSprite,
			int direction, final float width, final float height,
			TreeMap<Integer, Hitbox> orientedHitbox) {
		this(orientedSprite, direction, new TreeMap<Integer, Vector2>() {{
			put(0, new Vector2(width, height));
		}}, orientedHitbox);
	}

	public AnimatedActor(TreeMap<Integer, Animation> orientedSprite,
			int direction, TreeMap<Integer, Vector2> orientedSize,
			TreeMap<Integer, Hitbox> orientedHitbox) {
		this.orientedSize = orientedSize;
		this.orientedSprite = orientedSprite;
		this.orientedHitbox = orientedHitbox;
		this.direction = direction;
		updateSize();
		updateSprite();
		updateHitbox();
		setOrigin(Align.center);
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public StageScreen getScreen() {
		return screen;
	}

	public void setScreen(StageScreen screen) {
		this.screen = screen;
	}

	@Override
	public void setSize(float width, float height) {
		size.set(width, height);
		super.setSize(width, height);
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
	}

	public void setHitbox(Hitbox hitbox) {
		orientedHitbox.clear();
		orientedHitbox.put(0, hitbox);
		updateHitbox();
	}

	public int getDirection() {
		return direction;
	}

	public final void setDirection(int direction) {
		final int oldDirection = this.direction;
		this.direction = direction;
		if (direction != oldDirection) directionChanged();
	}

	protected void updateSize() {
		size = !orientedSize.isEmpty()
				? orientedSize.floorEntry(direction).getValue() : null;
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

	public void setTextureFromAtlas(String name){
		setSprite(getScreen().atlas.findRegion(name));
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
