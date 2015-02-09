package com.hhsfbla.cgs;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class PathFindingAction extends SequenceAction {

	public PathFindingAction(Level level, Actor a, int x, int y,
			float durationEach) {
		super();
		CellPath cp = level.grid.getPath((int) a.getX(), (int) a.getY(), x, y);
		float oldX = a.getX();
		float oldY = a.getY();
		int i = -1;
		while(true){
			i++;
			Cell c = cp.array.get(i);
			if(i == cp.array.size - 1){
				this.addAction(Actions.moveTo(c.x, c.y, durationEach));
				break;
			}
			Cell n = cp.array.get(i + 1);
			if(c.x - oldX == n.x - c.x && c.y - oldY == n.y - c.y)
				continue;
			oldX = c.x;
			oldY = c.y;
			this.addAction(Actions.moveTo(c.x, c.y, durationEach));
		}
	}

}
