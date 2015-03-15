package com.hhsfbla.cgs;

import com.badlogic.gdx.graphics.g2d.Batch;

public class FileStack extends Obstacle {
	private static final int MAX_HEALTH = 200;

	private int health = MAX_HEALTH;
	public int enemiesTargettingMe = 0;
	private HealthBar healthBar = new HealthBar();
	public int[] slots = {0,0,0,0,0,0,0,0};
	final static private int maxPerSlot = 1;

	public FileStack() {
		setOriginY(1/3f);
		updateOrientedSprite();
	}

	public void damage(int damage) {
		setHealth(health - damage);
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
		updateOrientedSprite();
	}

	protected void updateOrientedSprite() {
		if (health >= MAX_HEALTH * 3 / 4) {
			setSprite(Images.get("filestack-4.png"));
		} else if (health >= MAX_HEALTH / 2) {
			setSprite(Images.get("filestack-3.png"));
		} else if (health >= MAX_HEALTH / 4) {
			setSprite(Images.get("filestack-2.png"));
		} else if (health > 0) {
			setSprite(Images.get("filestack-1.png"));
		} else {
			setSprite(Images.get("filestack-0.png"));
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		healthBar.draw(batch, parentAlpha, getX(), getY(), health, MAX_HEALTH);
	}
	
	public SlottedCellPathContainer getPathToObstacle(int x, int y, AnimatedActor a) {
		int len = Integer.MAX_VALUE;
		CellPath shortest = null;
		int ind = 0;
		int uind = -1;
		for(int i = Math.round(x - 1); i <= Math.round(x + 1); i++) {
			for(int j = Math.round(y - 1); j <= Math.round(y + 1); j++) {
				if(i == Math.round(x) && j == Math.round(y))
					continue;
				if(slots[ind] == maxPerSlot ||
						i < 0 || j < 0 || i >= Level.GRID_COLS || j >= Level.GRID_ROWS) {
					ind++;
					continue;
				}
				final CellPath cp = a.getLevel().grid.getPath(
						Math.round(a.getX()), Math.round(a.getY()), i, j);
				if(cp.array.size == 0) continue;
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
