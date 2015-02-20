package com.hhsfbla.cgs;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class PathFindingAction extends SequenceAction {
	
	public PathFindingAction(Level level, Actor a, int x, int y, int endOffset) {
		CellPath cp = level.grid.getPath((int) a.getX(), (int) a.getY(), x, y);
		if (cp.array.size == 0 || cp.array.size - endOffset == 1)
			return;
		float oldX = a.getX();
		float oldY = a.getY();
		int i = 0;
		while (true) {
			i++;
			Cell c = cp.array.get(i);
			if (i == cp.array.size - 1 - endOffset) {
				addAction(new AnimatedMoveToAction(c.x, c.y));
				return;
			}
			Cell n = cp.array.get(i + 1);
			boolean cont = (c.x - oldX == n.x - c.x && c.y - oldY == n.y - c.y); //combine multiple moves in the same direction
			oldX = c.x;
			oldY = c.y;
			if (cont)
				continue;
			addAction(new AnimatedMoveToAction(c.x, c.y));
		}
	}
	
	public PathFindingAction(Level level, Actor a, float x, float y) {
		this(level, a, x, y, 0);
	}
	
	public PathFindingAction(Level level, Actor a, float x, float y, int endOffset) {
		this(level, a, (int) x, (int) y, endOffset);
	}
	
}
