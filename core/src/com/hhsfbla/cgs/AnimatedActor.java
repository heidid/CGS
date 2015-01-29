package com.hhsfbla.cgs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class AnimatedActor extends Actor {
	private Animation sprite;
	private float animationStateTime;

	public AnimatedActor() {
		super();
	}

	public AnimatedActor(Animation sprite) {
		setSprite(sprite);
	}

	public AnimatedActor(TextureRegion sprite) {
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
