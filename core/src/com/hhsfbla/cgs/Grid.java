package com.hhsfbla.cgs;

import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.utils.Array;

public class Grid {
	CellGraph ng;
	Level l;
	Array<Cell> cells;
	IndexedAStarPathFinder<Cell> finder;
	CellHeuristic h;
	
	public Grid(CellGraph ng, Level l) {
		super();
		this.ng = ng;
		this.l = l;
	}
	
	public void updateBlocked(){
		for (Obstacle o : l.getObstacles())
			cells.get((int) (o.getX() * Level.GRID_COLS) + (int) (o.getY())).blocked = true;
	}
	
	public void updateConnections(){
		for (int i = 0; i < cells.size; i++) {
			Cell cell = cells.get(i);
			Array<CellConnection> possibles = new Array<CellConnection>();
			for (int x = -1; x < 1; x++) {
				for (int y = -1; y < 1; y++) {
					if (x == 0 && y == 0)
						continue;
					float dist = 1.0f;
					if ((Math.abs(x) + Math.abs(y)) == 2)
						dist = (float) (1 / Math.sqrt(2));
					CellConnection c = new CellConnection(cell,
							cells.get((cell.x + x) * Level.GRID_COLS
									+ (cell.y + y)), dist);
					possibles.add(c);
				}
			}
			cell.possibles = possibles;
		}
	}

	public void generate() {
		cells = new Array<Cell>();
		for (int i = 0; i < Level.GRID_COLS; i++) {
			for (int j = 0; j < Level.GRID_ROWS; j++) {
				cells.add(new Cell(i * Level.GRID_COLS + j, false, i, j));
			}
		}
		updateBlocked();
		updateConnections();
		ng = new CellGraph(cells);
		finder = new IndexedAStarPathFinder<Cell>(ng);
		h = new CellHeuristic();
	}
	
	public CellPath getPath(int x1, int y1, int x2, int y2){
		CellPath ret = new CellPath();
		finder.searchNodePath(cells.get(x1 * Level.GRID_COLS + y1), cells.get(x2 * Level.GRID_COLS + y2), h, ret);
		return ret;
	}

}
