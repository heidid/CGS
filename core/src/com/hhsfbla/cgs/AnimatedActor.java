package com.hhsfbla.cgs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class AnimatedActor extends Actor {
	private Animation sprite;
	private float animationStateTime;
	private Rectangle bounds;

	public AnimatedActor() {
		super();
		bounds = new Rectangle(getX(), getY(), getWidth(), getHeight());
		setSize(1, 1);
	}

	public AnimatedActor(Animation sprite) {
		this();
		setSprite(sprite);
	}

	public AnimatedActor(TextureRegion sprite) {
		this();
		setSprite(sprite);
	}

	public Animation getSpriteAnimation() {
		return sprite;
	}

	public TextureRegion getCurrentSprite() {
		return sprite != null ? sprite.getKeyFrame(animationStateTime) : null;
	}

	public final void setSprite(Animation sprite) {
		this.sprite = sprite;
		animationStateTime = 0;
	}

	public final void setSprite(TextureRegion sprite) {
		setSprite(new Animation(0, sprite));
	}

	public Rectangle getBounds() {
		return bounds;
	}

	@Override
	protected void positionChanged() {
		super.positionChanged();
		bounds.setPosition(getX(), getY());
	}

	@Override
	protected void sizeChanged() {
		super.sizeChanged();
		bounds.setSize(getWidth(), getHeight());
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		if (sprite != null) {
			final Stage stage = getStage();
			final TextureRegion image = getCurrentSprite();
			final float gridWidth = stage.getWidth() / Level.GRID_ROWS;
			final float gridHeight = stage.getHeight() / Level.GRID_COLS;
			final float height = image.getRegionHeight() * gridWidth / image.getRegionWidth();
			batch.draw(image, getX() * gridWidth, getY() * gridHeight, getOriginX(), getOriginY(),
					gridWidth, height, getScaleX(), getScaleY(), getRotation());
			animationStateTime += Gdx.graphics.getDeltaTime();
		}
	}
}
