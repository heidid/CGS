package com.hhsfbla.cgs;

import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class PathfindingAction extends SequenceAction {
	private int x;
	private int y;
	private int endOffset;
	private CellPath cp = null;

	public PathfindingAction(float x, float y) {
		this(x, y, 0);
	}

	public PathfindingAction(float x, float y, int endOffset) {
		this.x = Math.round(x);
		this.y = Math.round(y);
		this.endOffset = endOffset;
	}
	
	public PathfindingAction(CellPath cp) {
		this.cp = cp;
	}

	private void init() {
		final AnimatedActor a = (AnimatedActor) getActor();
		int oldX = Math.round(a.getX());
		int oldY = Math.round(a.getY());
		
		if(cp == null) {
			cp = a.getLevel().grid.getPath(
					Math.round(oldX), oldY, x, y);
		}

		if (cp.array.size == 0 || cp.array.size - endOffset == 1) return;

		int i = 0;
		//System.out.println("PATH from "+oldX+","+oldY);
		while (true) {
			i++;

			final Cell c = cp.array.get(i);
			if (i == cp.array.size - 1 - endOffset) {
				addAction(new AnimatedMoveToAction(c.x, c.y));
				//System.out.println(c.x+","+c.y);
				return;
			}
			final Cell n = cp.array.get(i + 1);

			// combine multiple moves in the same direction
			final boolean cont = (c.x - oldX == n.x - c.x && c.y - oldY == n.y - c.y);
			oldX = c.x;
			oldY = c.y;

			if (cont) continue;
			addAction(new AnimatedMoveToAction(c.x, c.y));
			//System.out.println(c.x+","+c.y);
		}
	}

	@Override
	public boolean act(float delta) {
		if (getActions().size == 0) init();
		return super.act(delta);
	}
	
}
