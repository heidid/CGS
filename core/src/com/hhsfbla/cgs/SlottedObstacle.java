package com.hhsfbla.cgs;

public class SlottedObstacle extends Obstacle {

	public int[] slots = {0,0,0,0,0,0,0,0};
	private int maxPerSlot;

	public SlottedObstacle(int maxPerSlot) {
		super();
		this.maxPerSlot = maxPerSlot;
	}

	public SlottedCellPathContainer getPathToObstacle(int x, int y, AnimatedActor a) {
		int len = Integer.MAX_VALUE;
		CellPath shortest = null;
		int ind = 0;
		int uind = -1;
		//System.out.println("START: "+this.getX()+","+this.getY());
		for(int i = Math.round(x - 1); i <= Math.round(x + 1); i++) {
			for(int j = Math.round(y - 1); j <= Math.round(y + 1); j++) {
				if(i == Math.round(x) && j == Math.round(y))
					continue;
				if(slots[ind] == maxPerSlot) {
					//System.out.println("TAKEN: "+i+","+j+"|"+slots[ind]+"|"+ind);
					ind++;
					continue;
				}
				if(i < 0 || j < 0 || i >= Level.GRID_COLS || j >= Level.GRID_ROWS) {
					ind++;
					continue;
				}
				final CellPath cp = a.getLevel().grid.getPath(
						Math.round(a.getX()), Math.round(a.getY()), i, j);
				if(cp.array.size == 0) {
					ind++;
					continue;
				}
				if(cp.array.size < len) {
					len = cp.array.size;
					shortest = cp;
					uind = ind;
				}
				ind++;
			}
		}
		return new SlottedCellPathContainer(shortest, uind);
	}
}
