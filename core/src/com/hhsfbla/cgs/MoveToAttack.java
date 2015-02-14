package com.hhsfbla.cgs;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * An action that moves an Enemy to an Actor and attacks it
 *
 */
public class MoveToAttack extends PathFindingAction {

	public MoveToAttack(Level level, Actor a, float x, float y) {
		super(level, a, (int) x, (int) y, 1); //the 1 means the pathfinding will stop right before the target cell is reached
	}

}
