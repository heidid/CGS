package com.hhsfbla.cgs;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.utils.Array;

public class NodeGraph implements IndexedGraph<Node> {
	Array<Node> nodes;
	@Override
	public Array<Connection<Node>> getConnections(Node fromNode) {
		return fromNode.getConnections();
	}

	@Override
	public int getNodeCount() {
		return nodes.size;
	}

}
