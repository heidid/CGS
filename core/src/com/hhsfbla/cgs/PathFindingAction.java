package com.hhsfbla.cgs;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class PathFindingAction extends SequenceAction {

	public PathFindingAction(Level level, Actor a, Vector2 fin, float durationEach) {
		super();
		CellPath cp = level.grid.getPath((int)a.getX(), (int)a.getY(), (int)fin.x, (int)fin.y);
		for(Cell c : cp.array){
			this.addAction(Actions.moveTo(c.x, c.y, durationEach));
		}
	}
	
}
