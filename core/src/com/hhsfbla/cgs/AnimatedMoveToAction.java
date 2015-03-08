package com.hhsfbla.cgs;

import com.badlogic.gdx.scenes.scene2d.Action;

/**
 * A MoveToAction that uses MoveActor animations
 */
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
		if (done) return true;

		final MovableActor actor = (MovableActor) getActor();

		if (!started) {
			// Round angle to nearest octilinear direction
			direction = actor.getDirectionFacing(x, y);
			actor.setMoving(direction);
			started = true;
		}

		final boolean horizontal = direction % 180 == 0;
		final boolean vertical = direction % 180 == 90;

		final float distX = Math.abs(actor.getX() - x);
		final float distY = Math.abs(actor.getY() - y);
		final float epsilon = actor.getSpeed() * delta;

		// stopping condition
		if (vertical && distY < epsilon				// same Y if vertical
				|| horizontal && distX < epsilon	// same X if horizontal
				|| !vertical && !horizontal			// same X or Y if diagonal
				&& (distX < epsilon || distY < epsilon)) {
			actor.setIdle();
			done = true;
		}

		return done;
	}
}
