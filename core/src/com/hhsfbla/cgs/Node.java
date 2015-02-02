package com.hhsfbla.cgs;
import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.*;
import com.badlogic.gdx.utils.Array;
public class Node implements IndexedNode<Node> {
	int index;
	Array<Connection<Node>> connections;
	
	public Node(int index, Array<Connection<Node>> connections) {
		super();
		this.index = index;
		this.connections = connections;
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public Array<Connection<Node>> getConnections() {
		return connections;
	}

}
