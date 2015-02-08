package com.hhsfbla.cgs;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class PathFindingAction extends SequenceAction {

	public PathFindingAction(Level level, Actor a, int x, int y, float durationEach) {
		super();
		CellPath cp = level.grid.getPath((int)a.getX(), (int)a.getY(), x, y);
		for(Cell c : cp.array){
			this.addAction(Actions.moveTo(c.x, c.y, durationEach));
			System.out.println(c.x+","+c.y);
		}
	}
	
}
