package com.hhsfbla.cgs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

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
		return sprite.getKeyFrame(animationStateTime);
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
			final TextureRegion image = getCurrentSprite();
			batch.draw(image, getX(), getY(), getOriginX(), getOriginY(),
					image.getRegionWidth(), image.getRegionHeight(), getScaleX(), getScaleY(), getRotation());
			animationStateTime += Gdx.graphics.getDeltaTime();
		}
	}

}
