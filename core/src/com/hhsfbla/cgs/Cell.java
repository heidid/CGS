package com.hhsfbla.cgs;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.*;
import com.badlogic.gdx.utils.Array;

public class Cell implements IndexedNode<Cell> {
	int index;
	Array<CellConnection> possibles;
	Array<Connection<Cell>> temp = new Array<Connection<Cell>>();
	boolean blocked;
	int x, y;

	public Cell(int index, boolean blocked, int x, int y) {
		super();
		this.index = index;
		this.blocked = blocked;
		this.x = x;
		this.y = y;
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public Array<Connection<Cell>> getConnections() {
		temp.clear();
		for (Connection<Cell> c : possibles)
			if (!c.getToNode().blocked)
				temp.add(c);
		return temp;
	}

}
