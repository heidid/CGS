package com.hhsfbla.cgs;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.utils.Array;

public class CellGraph implements IndexedGraph<Cell> {
	Array<Cell> nodes;
	
	public CellGraph(Array<Cell> nodes) {
		super();
		this.nodes = nodes;
	}

	@Override
	public Array<Connection<Cell>> getConnections(Cell fromNode) {
		return fromNode.getConnections();
	}

	@Override
	public int getNodeCount() {
		return nodes.size;
	}

}
