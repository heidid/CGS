package com.hhsfbla.cgs;

public class FloorSwitch extends Switch {
	private AnimatedActor presser;

	public FloorSwitch() {
		this(false);
	}

	public FloorSwitch(boolean on) {
		super(on);
		setOffSprite(Images.get("ground-switch.png"));
		setOnSprite(Images.get("ground-switch-pressed.png"));
	}

	public AnimatedActor getPresser() {
		return presser;
	}

	public void setPresser(AnimatedActor presser) {
		if (this.presser == null && presser != null) setOn(!isOn());
		this.presser = presser;
	}

	@Override
	protected void resolveCollision(AnimatedActor actor) {
		if (actor instanceof Player
				&& getHitbox().contains(actor.getX(), actor.getY())) {
			setPresser(actor);
		}
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		if (presser != null && !getHitbox()
				.contains(presser.getX(), presser.getY())) {
			setPresser(null);
		}
	}
}
