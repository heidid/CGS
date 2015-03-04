package com.hhsfbla.cgs;

public class ExitPort extends Port {
	public ExitPort() {
		this(DIR_RIGHT);
	}

	public ExitPort(int direction) {
		super(direction);
	}

	@Override
	protected void resolveCollision(AnimatedActor actor) {
		if (actor instanceof Player) {
			actor.addAction(((Player) actor).new ExitAction());
		}
	}
}
