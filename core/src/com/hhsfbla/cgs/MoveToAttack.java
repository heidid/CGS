package com.hhsfbla.cgs;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class MoveToAttack extends PathFindingAction {
	
	public MoveToAttack(Level level, Actor a, int x, int y,
			float durationEach) {
		super(level, a, x, y, durationEach, 1);
	}
	
}
