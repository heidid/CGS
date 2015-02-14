package com.hhsfbla.cgs;

import java.util.Iterator;

import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.utils.Array;

//Standard Path implementation
public class CellPath implements GraphPath<Cell> {

	Array<Cell> array = new Array<Cell>();

	@Override
	public Iterator<Cell> iterator() {
		return array.iterator();
	}

	@Override
	public int getCount() {
		return array.size;
	}

	@Override
	public Cell get(int index) {
		return array.get(index);
	}

	@Override
	public void add(Cell node) {
		array.add(node);
	}

	@Override
	public void clear() {
		array.clear();
	}

	@Override
	public void reverse() {
		array.reverse();
	}

}
