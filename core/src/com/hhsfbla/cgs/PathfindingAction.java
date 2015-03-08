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
		this.x = (int) x;
		this.y = (int) y;
		this.endOffset = endOffset;
	}
	
	public PathfindingAction(CellPath cp) {
		this.cp = cp;
	}

	private void init() {
		final AnimatedActor a = (AnimatedActor) getActor();
		if(cp == null) {
			cp = a.getLevel().grid.getPath(
					(int) a.getX(), (int) a.getY(), x, y);
			
		}

		if (cp.array.size == 0 || cp.array.size - endOffset == 1) return;

		float oldX = a.getX();
		float oldY = a.getY();

		int i = 0;
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
