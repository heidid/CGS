package com.hhsfbla.cgs;

import com.badlogic.gdx.ai.pfa.Connection;

public class CellConnection implements Connection<Cell> {
	Cell fromCell;
	Cell toCell;
	float cost;
	
	public CellConnection(Cell fromCell, Cell toCell, float cost) {
		super();
		this.fromCell = fromCell;
		this.toCell = toCell;
		this.cost = cost;
	}

	@Override
	public float getCost() {
		return cost;
	}

	@Override
	public Cell getFromNode() {
		return fromCell;
	}

	@Override
	public Cell getToNode() {
		return toCell;
	}
	
}
