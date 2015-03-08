package com.hhsfbla.cgs;

import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

/**
 * An action that moves an Enemy to an Actor and attacks it
 *
 */
public class MoveToAttack extends SequenceAction {
	float x, y;
	public MoveToAttack(float x, float y) {
		this.x = x;
		this.y = y;
	}
	public void init(){
		final AnimatedActor a = (AnimatedActor) getActor();
		int mx = -1, my = -1;
		int len = Integer.MAX_VALUE;
		for(int i = (int) (x - 1); i <= (int) (x + 1); i++) {
			for(int j = (int) (y - 1); j <= (y + 1); j++) {
				if((i == x && j == y) || (i != x && j != y) || i < 0 || j < 0 || i >= Level.GRID_ROWS || j >= Level.GRID_COLS)
					continue;
				final CellPath cp = a.getLevel().grid.getPath(
						(int) a.getX(), (int) a.getY(), i, j);
				if(cp.array.size == 0) continue;
				if(cp.array.size < len) {
					len = cp.array.size;
					mx = i;
					my = j;
				}
			}
		}
		if (mx == -1)
			return;
		addAction(new PathfindingAction(mx, my, 0));
	}
	@Override
	public boolean act(float delta) {
		if (getActions().size == 0) init();
		return super.act(delta);
	}
}
