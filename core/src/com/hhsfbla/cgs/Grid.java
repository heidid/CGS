package com.hhsfbla.cgs;

import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.utils.Array;

/**
 * A Grid used for pathfinding in Level
 */
public class Grid {
	CellGraph ng;
	Level l;
	Array<Cell> cells;
	IndexedAStarPathFinder<Cell> finder; //PathFinder to look through the CellGraph
	CellHeuristic h; //Heuristic to optimize algorithm (approximation for distance), we use the distance formula

	public Grid(Level l) {
		super();
		this.l = l;
	}

	public Cell getCell(int x, int y) {
		return cells.get(y * Level.GRID_COLS + x);
	}

	public void updateBlocked(){
		for (Cell c : cells)
			c.blocked = false;
		for (Obstacle o : l.getObstacles()){
			if(o.getY() >= Level.GRID_ROWS || o.getX() >= Level.GRID_COLS || !o.isBlocked()) //bound check
				continue;
			getCell((int) (o.getX()), (int) (o.getY())).blocked = true;
		}
	}

	public void updateConnections(){
		for (int i = 0; i < cells.size; i++) {
			Cell cell = cells.get(i);
			Array<CellConnection> possibles = new Array<CellConnection>();
			for (int x = -1; x <= 1; x++) {
				for (int y = -1; y <= 1; y++) {
					if((cell.x + x < 0 || cell.x + x >= Level.GRID_COLS) || //bound checks
							(cell.y + y < 0 || cell.y + y >= Level.GRID_ROWS) ||
							(x == 0 && y == 0)) //check we aren't on the current cell
						continue;
					float dist = 1.0f;
					if ((Math.abs(x) + Math.abs(y)) == 2)
						dist = (float) (Math.sqrt(2));
					CellConnection c = new CellConnection(cell,
							getCell((cell.x + x), (cell.y + y)), dist);
					possibles.add(c);
				}
			}
			cell.possibles = possibles;
		}
	}

	public void generate() {
		cells = new Array<Cell>();
		//create 2D cell array on a 1D array
		for (int i = 0; i < Level.GRID_ROWS; i++) {
			for (int j = 0; j < Level.GRID_COLS; j++) {
				cells.add(new Cell(i * Level.GRID_COLS + j, false, j, i, this));
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
		finder.searchNodePath(getCell(x1, y1), getCell(x2, y2), h, ret);
		return ret;
	}

}
