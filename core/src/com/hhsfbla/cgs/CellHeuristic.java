package com.hhsfbla.cgs;

import com.badlogic.gdx.ai.pfa.Heuristic;

public class CellHeuristic implements Heuristic<Cell> {
	
	//Distance formula
	@Override
	public float estimate(Cell node, Cell endNode) {
		return (float) Math.sqrt((endNode.x - node.x) * (endNode.x - node.x)
				+ (endNode.y - node.y) * (endNode.y - node.y));
	}

}
