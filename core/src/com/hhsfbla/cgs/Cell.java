package com.hhsfbla.cgs;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.*;
import com.badlogic.gdx.utils.Array;

public class Cell implements IndexedNode<Cell> {
	int index;
	Array<CellConnection> possibles;
	Array<Connection<Cell>> temp = new Array<Connection<Cell>>();
	boolean blocked; //is this cell able to be passed through
	Grid g;
	int x, y;

	public Cell(int index, boolean blocked, int x, int y, Grid g) {
		super();
		this.index = index;
		this.blocked = blocked;
		this.x = x;
		this.y = y;
		this.g = g;
	}

	@Override
	public int getIndex() {
		return index;
	}

	//Update blocked field of cells
	public void updateConnections(){
		temp.clear();
		for (Connection<Cell> c : possibles) {
			if (c.getToNode().blocked)
				continue;
			if (Math.abs(this.x - c.getToNode().x) + Math.abs(this.y - c.getToNode().y) == 2) { //Can't go diagonally through an obstacle
				//if (g.getCell(this.x, c.getToNode().y).blocked || g.getCell(c.getToNode().x, this.y).blocked)
					continue;
			}
			temp.add(c);
		}
	}
	
	@Override
	public Array<Connection<Cell>> getConnections() {
		updateConnections();
		return temp;
	}

}
