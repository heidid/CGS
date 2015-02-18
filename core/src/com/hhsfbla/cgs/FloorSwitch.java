package com.hhsfbla.cgs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class FloorSwitch extends Obstacle {
	private static final TextureRegion offSprite = new TextureRegion(
			new Texture(Gdx.files.internal("ground-switch.png")));
	private static final TextureRegion onSprite = new TextureRegion(
			new Texture(Gdx.files.internal("ground-switch-pressed.png")));

	private boolean on;
	private AnimatedActor presser;
	private Obstacle link;

	public FloorSwitch() {
		setBlocked(false);
		setOn(false);
	}

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
		setSprite(on ? onSprite : offSprite);
		if (on) {
			// TODO: Do something with link
		}
	}

	@Override
	protected void updateSprite() {
		super.updateSprite();
	}

	public AnimatedActor getPresser() {
		return presser;
	}

	public void setPresser(AnimatedActor presser) {
		if (this.presser == null && presser != null) setOn(!on);
		this.presser = presser;
	}

	@Override
	protected void resolveCollision(AnimatedActor actor) {
		setPresser(actor);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		if (presser != null && !presser.getHitbox().overlaps(getHitbox())) {
			setPresser(null);
		}
	}
}
