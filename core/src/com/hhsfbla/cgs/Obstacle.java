package com.hhsfbla.cgs;

public class Obstacle extends AnimatedActor {
	private boolean blocked;

	public Obstacle() {
		blocked = true;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
}
