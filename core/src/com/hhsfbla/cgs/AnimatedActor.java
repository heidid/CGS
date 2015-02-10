package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
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

	public AnimatedActor(TreeMap<Integer, Animation> orientedSprite,
			int direction) {
		this(orientedSprite, direction, 1, 1);
	}

	public AnimatedActor(TreeMap<Integer, Animation> orientedSprite,
			int direction, float width, float height) {
		this.orientedSprite = orientedSprite;
		this.direction = direction;
		bounds = new Rectangle(getX(), getY(), getWidth(), getHeight());
		setSize(width, height);
		setOrigin(Align.center);
		updateSprite();
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public TextureRegion getCurrentSpriteFrame() {
		return sprite != null ? sprite.getKeyFrame(animationStateTime) : null;
	}

	public final Animation getSprite() {
		return sprite;
	}

	public final Animation getSprite(int direction) {
		return !orientedSprite.isEmpty()
				? orientedSprite.floorEntry(direction).getValue() : null;
	}

	public TreeMap<Integer, Animation> getOrientedSprite() {
		return orientedSprite;
	}

	public final void setSprite(TreeMap<Integer, Animation> orientedSprite) {
		this.orientedSprite = orientedSprite;
		updateSprite();
	}

	public final void setSprite(Animation sprite) {
		orientedSprite.clear();
		orientedSprite.put(0, sprite);
		updateSprite();
	}

	public final void setSprite(TextureRegion sprite) {
		setSprite(new Animation(0, sprite));
	}

	public StageScreen getScreen() {
		return screen;
	}

	public void setScreen(StageScreen screen) {
		this.screen = screen;
	}

	protected void updateSprite() {
		final Animation oldSprite = this.sprite;
		this.sprite = getSprite(direction);
		if (sprite != oldSprite) animationStateTime = 0;
	}

	public int getDirection() {
		return direction;
	}

	public final void setDirection(int direction) {
		final int oldDirection = this.direction;
		this.direction = direction;
		if (direction != oldDirection) directionChanged();
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

	public Texture setTextureFromAtlas(String name){
		return getScreen().atlas.findRegion(name).getTexture();
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
