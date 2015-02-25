package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Switch extends Obstacle {
	private boolean on;
	private TreeMap<Integer, Animation> offSprite;
	private TreeMap<Integer, Animation> onSprite;
	private AnimatedActor presser;
	private Obstacle link;

	public Switch() {
		setBlocked(false);
	}

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
		updateOn();
		if (on) {
			// TODO: Do something with link
		}
	}

	public TreeMap<Integer, Animation> getOffSprite() {
		return offSprite;
	}

	public TreeMap<Integer, Animation> getOnSprite() {
		return onSprite;
	}

	public void setOffSprite(TreeMap<Integer, Animation> offSprite) {
		this.offSprite = offSprite;
	}

	public void setOffSprite(final Animation offSprite) {
		setOffSprite(new TreeMap<Integer, Animation>(){{ put(0, offSprite); }});
	}

	public void setOffSprite(TextureRegion offSprite) {
		setOffSprite(new Animation(0, offSprite));
	}

	public void setOnSprite(TreeMap<Integer, Animation> onSprite) {
		this.onSprite = onSprite;
	}

	public void setOnSprite(final Animation onSprite) {
		setOnSprite(new TreeMap<Integer, Animation>(){{ put(0, onSprite); }});
	}

	public void setOnSprite(TextureRegion onSprite) {
		setOnSprite(new Animation(0, onSprite));
	}

	protected void updateOn() {
		setSprite(on ? onSprite : offSprite);
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
		if (actor instanceof Player) setPresser(actor);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		if (presser != null && !presser.getHitbox().overlaps(getHitbox())) {
			setPresser(null);
		}
	}
}
