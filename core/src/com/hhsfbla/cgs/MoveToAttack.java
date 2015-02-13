package com.hhsfbla.cgs;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class MoveToAttack extends PathFindingAction {
	
	public MoveToAttack(Level level, Actor a, float x, float y) {
		super(level, a, (int) x, (int) y, 1);
	}
	
}
