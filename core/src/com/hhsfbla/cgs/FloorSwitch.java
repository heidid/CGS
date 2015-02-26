package com.hhsfbla.cgs;

public class FloorSwitch extends Switch {
	public FloorSwitch() {
		this(false);
	}

	public FloorSwitch(boolean on) {
		super(on);
		setOffSprite(Images.get("ground-switch.png"));
		setOnSprite(Images.get("ground-switch-pressed.png"));
	}

	@Override
	protected void resolveCollision(AnimatedActor actor) {
		if (actor instanceof Player) setPresser(actor);
	}
}
