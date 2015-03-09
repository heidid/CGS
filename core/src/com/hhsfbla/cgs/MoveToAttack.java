package com.hhsfbla.cgs;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

/**
 * An action that moves an Enemy to an Actor and attacks it
 *
 */
public class MoveToAttack extends SequenceAction {
	class FaceAction extends Action {
		@Override
		public boolean act(float delta) {
			AnimatedActor aa = (AnimatedActor) (getActor());
			aa.setDirection(aa.getDirectionFacing(Math.round(x), Math.round(y)));
			return true;
		}
	}
	float x, y;
	CellPath cp;
	public MoveToAttack(float x, float y, CellPath cp) {
		this.x = x;
		this.y = y;
		this.cp = cp;
	}
	public MoveToAttack(float x, float y) {
		this.x = x;
		this.y = y;
		final AnimatedActor a = (AnimatedActor) getActor();
		cp = a.getLevel().grid.getPathToObstacle(Math.round(x), Math.round(y), a);
	}
	public void init() {
		addAction(new PathfindingAction(cp));
		addAction(new FaceAction());
	}
	@Override
	public boolean act(float delta) {
		if (getActions().size == 0) init();
		return super.act(delta);
	}
}
