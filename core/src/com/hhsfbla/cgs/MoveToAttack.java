package com.hhsfbla.cgs;

/**
 * An action that moves an Enemy to an Actor and attacks it
 *
 */
public class MoveToAttack extends PathfindingAction2 {

	public MoveToAttack(float x, float y) {
		super(x, y, 1); //the 1 means the pathfinding will stop right before the target cell is reached
	}

}
