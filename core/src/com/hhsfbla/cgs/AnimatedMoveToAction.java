package com.hhsfbla.cgs;

import com.badlogic.gdx.scenes.scene2d.Action;

public class AnimatedMoveToAction extends Action {
	private float x;
	private float y;
	private int direction;
	private boolean started;
	private boolean done;

	public AnimatedMoveToAction(float x, float y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean act(float delta) {
		if (!started) {
			direction = (int) (Math.round(Math.toDegrees(Math.atan2(
					y - actor.getY(), x - actor.getX())) / 45)) * 45;
			started = true;
		}

		if (done) return true;

		if (!started) {
			System.out.println("go to " + x + ", " + y + " at " + direction);
			started = true;
		}

		final MovableActor actor = (MovableActor) getActor();
		final float epsilon = actor.getSpeed() * delta;

		final boolean horizontal = direction % 180 == 0;
		final boolean vertical = direction % 180 == 90;

		final float distX = Math.abs(actor.getX() - x);
		final float distY = Math.abs(actor.getY() - y);

		if (done || vertical && distY < epsilon
				|| horizontal && distX < epsilon
				|| !vertical && !horizontal
				&& (distX < epsilon || distY < epsilon)) {
			actor.setIdle();
			done = true;
		} else {
			actor.setMoving(direction);
		}

		return done;
	}
}
