package com.hhsfbla.cgs;

import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.utils.Array;

public class Grid {
	CellGraph ng;
	Level l;
	Array<Cell> cells;
	IndexedAStarPathFinder<Cell> finder;
	CellHeuristic h;
	
	public Grid(Level l) {
		super();
		this.l = l;
	}
	
	public void updateBlocked(){
		for (Cell c : cells)
			c.blocked = false;
		for (Obstacle o : l.getObstacles()){
			if(o.getY() >= Level.GRID_ROWS || o.getX() >= Level.GRID_COLS)
				continue;
			cells.get((int) (o.getX()) + (int) (o.getY() * Level.GRID_COLS)).blocked = true;
		}
	}
	
	public void updateConnections(){
		for (int i = 0; i < cells.size; i++) {
			Cell cell = cells.get(i);
			Array<CellConnection> possibles = new Array<CellConnection>();
			for (int x = -1; x <= 1; x++) {
				for (int y = -1; y <= 1; y++) {
					if((cell.x + x < 0 || cell.x + x >= Level.GRID_COLS) ||
							(cell.y + y < 0 || cell.y + y >= Level.GRID_ROWS) ||
							(x == 0 && y == 0))
						continue;
					float dist = 1.0f;
					if ((Math.abs(x) + Math.abs(y)) == 2)
						dist = (float) (Math.sqrt(2));
					CellConnection c = new CellConnection(cell,
							cells.get((cell.y + y) * Level.GRID_COLS
									+ (cell.x + x)), dist);
					possibles.add(c);
				}
			}
			cell.possibles = possibles;
		}
	}

	public void generate() {
		cells = new Array<Cell>();
		for (int i = 0; i < Level.GRID_ROWS; i++) {
			for (int j = 0; j < Level.GRID_COLS; j++) {
				cells.add(new Cell(i * Level.GRID_COLS + j, false, j,i));
			}
		}
		updateBlocked();
		updateConnections();
		ng = new CellGraph(cells);
		finder = new IndexedAStarPathFinder<Cell>(ng);
		h = new CellHeuristic();
	}
	
	public CellPath getPath(int x1, int y1, int x2, int y2){
		updateBlocked();
		CellPath ret = new CellPath();
		finder.searchNodePath(cells.get(x1 + y1 * Level.GRID_COLS), cells.get(x2 + y2 * Level.GRID_COLS), h, ret);
		return ret;
	}

}
